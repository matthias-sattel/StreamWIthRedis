package de.matthiassattel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class RedisPublisherApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<RedisPublisherApplication>(*args)
        }
    }

}
