package de.matthiassattel.movie

import de.mathiassattel.movie.Movie
import de.mathiassattel.movie.MovieRating
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import java.util.stream.Collectors
import java.util.stream.Stream


@Repository
class MovieRepository {

    val MOVIE_LIST: List<Movie> = Stream.of(
        Movie(1, "Avengers End Game", "Marvel Studios"),
        Movie(2, "Avengers Infinity War", "Marvel Studios"),
        Movie(3, "Dark Knight", "Warner Bros"),
        Movie(4, "Pulp Fiction", "MiraMax"),
        Movie(5, "Fight Club", "Warner Bros"),
        Movie(6, "Good Fellas", "Warner Bros"),
        Movie(7, "Seven", "Warner Bros"),
        Movie(8, "Cast Away", "ImageMovers Playtone"),
        Movie(9, "Forest Gump", "The Tisch Company"),
        Movie(10, "King Kong", "Warner Bros"),
        Movie(11, "The Silence Of Lambs", "Strong Heart Productions"),
        Movie(12, "Usual Suspects", "PolyGram Filmed Entertainment"),
        Movie(13, "Green Mile", "Castle Rock Entertainment"),
        Movie(14, "No Country For Old Men", "Scott Rudin Productions"),
        Movie(15, "Train to Busan", "Next Entertainment World"),
        Movie(16, "Parasite", "Barunson E&A"),
        Movie(17, "Whiplash", "Sony Pictures"),
        Movie(18, "The Prestige", "Warner Bros"),
        Movie(19, "Joker", "Warner Bros"),
        Movie(20, "Old Boy", "Show East"),
        Movie(21, "I Saw Devil", "Peppermint and company"),
        Movie(22, "The Perfect Murder", "Warner Bros"),
        Movie(23, "The Chaser", "Snow Box"),
        Movie(24, "Goodwill Hunting", "Be Gentlemen"),
        Movie(25, "Snatch", "Columbia Pictures")
    ).collect(Collectors.toList())

    fun getRandomMovie(): MovieRating {
        val index: Int = ThreadLocalRandom.current().nextInt(0, 25)
        val movie = MOVIE_LIST[index]
        val random = Random()
        val value: Int = random.ints(0, 1000).findFirst().getAsInt()
        val rating: Double = random.doubles(1.0, 10.0).findFirst().getAsDouble()
        return MovieRating(movie, value % 2 == 0, value % 2 == 1, rating)
    }

}