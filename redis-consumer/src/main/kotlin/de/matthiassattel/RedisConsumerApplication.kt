package de.matthiassattel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisConsumerApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<RedisConsumerApplication>(*args)
        }
    }

}


