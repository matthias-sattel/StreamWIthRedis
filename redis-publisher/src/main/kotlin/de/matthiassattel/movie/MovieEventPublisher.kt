package de.matthiassattel.movie

import de.mathiassattel.movie.MovieDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.stream.ObjectRecord
import org.springframework.data.redis.connection.stream.StreamRecords
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicInteger


@Service
class MovieEventPublisher() {

    private val atomicInteger = AtomicInteger(0)

    @Value("\${stream.key}")
    private val streamKey: String = ""

    @Autowired
    private var movieRepository: MovieRepository? = null

    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun reactiveRedisTemplate(
        redisConnectionFactory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, MovieDetails> {
        val keySerializer = StringRedisSerializer()
        val valueSerializer: Jackson2JsonRedisSerializer<MovieDetails> =
            Jackson2JsonRedisSerializer(MovieDetails::class.java)
        val builder: RedisSerializationContext.RedisSerializationContextBuilder<String, MovieDetails> =
            RedisSerializationContext.newSerializationContext<String, MovieDetails>(keySerializer)
        val context: RedisSerializationContext<String, MovieDetails> = builder.value(valueSerializer).build()
        return ReactiveRedisTemplate<String, MovieDetails>(redisConnectionFactory, context)
    }

    //@Scheduled(fixedRateString = "\${publish.rate}")
    fun publishEvent(reactiveRedisTemplate: ReactiveRedisTemplate<String, MovieDetails>) {
        val movieDetails: MovieDetails = movieRepository!!.getRandomMovie()
        System.out.println("Movie Details :: ${movieDetails.movie.name}")
        System.out.println("Stream Key is :: $streamKey")
        val record: ObjectRecord<String, MovieDetails> = StreamRecords
            .newRecord()
            .ofObject(movieDetails)
            .withStreamKey(streamKey)
        reactiveRedisTemplate
            .opsForStream<String, MovieDetails>()
            .add(record)
            .subscribe(System.out::println)
        atomicInteger.incrementAndGet()
    }

    @Scheduled(fixedRate = 10000)
    fun showPublishedEventsSoFar() {
        System.out.println(("Total Events :: " + atomicInteger.get()))
    }

}