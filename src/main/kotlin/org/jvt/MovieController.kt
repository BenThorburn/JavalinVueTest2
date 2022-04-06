package org.jvt

import io.javalin.http.Context
import io.javalin.http.NotFoundResponse

data class Movie(val id: String, val name: String, val email: String, val movieDetails: MovieDetails?)
data class MovieDetails(val dateOfBirth: String, val salary: String)

val movies = setOf<Movie>(
    Movie(id = "1", name = "John", email = "john@fake.co", movieDetails = MovieDetails("21.02.1964", "2773 JB")),
    Movie(id = "2", name = "Mary", email = "mary@fake.co", movieDetails = MovieDetails("12.05.1994", "1222 JB")),
    Movie(id = "3", name = "Dave", email = "dave@fake.co", movieDetails = MovieDetails("01.05.1984", "1833 JB"))
)

object MovieController {
    fun getAll(ctx: Context) {
        ctx.json(movies.map { it.copy(movieDetails = null) }) // remove sensitive information
    }

    fun getOne(ctx: Context) {
        val movie = movies.find { it.id == ctx.pathParam("movie-id") } ?: throw NotFoundResponse()
        ctx.json(movie)
    }
}