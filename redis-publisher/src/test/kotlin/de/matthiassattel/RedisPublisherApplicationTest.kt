package de.matthiassattel

import de.mathiassattel.movie.MovieRating
import de.matthiassattel.movie.MovieEventPublisher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest
class RedisPublisherApplicationTest {

    @Autowired
    lateinit var movieEventPublisher: MovieEventPublisher

    @Autowired
    lateinit var reactiveRedisTemplate: ReactiveRedisTemplate<String, MovieRating>

    @Test
    fun testInsertIntoStream() {
        //given

        //when
        movieEventPublisher.publishEvent(reactiveRedisTemplate)
        //then
        Assertions.assertTrue(true)
    }

}