package de.matthiassattel

import de.mathiassattel.movie.MovieDetails
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.connection.stream.ObjectRecord
import org.springframework.data.redis.stream.StreamListener


@Configuration
class RedisConfig {

    @Value("\${stream.key}")
    private val streamKey: String = ""

    private val streamListener: StreamListener<String, ObjectRecord<String, MovieDetails>>? = null

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory? {
        return LettuceConnectionFactory()
    }

//    @Bean
//    @Throws(UnknownHostException::class)
//    fun subscription(redisConnectionFactory: JedisConnectionFactory?): Subscription? {
//        val options = StreamMessageListenerContainerOptions
//            .builder()
//            .pollTimeout(Duration.ofSeconds(1))
//            .targetType<MovieDetails>(MovieDetails::class.java)
//            .build()
//        val listenerContainer = StreamMessageListenerContainer
//            .create(redisConnectionFactory, options)
//        val subscription: Subscription = listenerContainer.receive(
//            Consumer.from(
//                streamKey, InetAddress.getLocalHost()
//                    .hostName
//            ),
//            StreamOffset.create<String>(streamKey, ReadOffset.lastConsumed()),
//            streamListener
//        )
//        listenerContainer.start()
//        return subscription
//    }


}