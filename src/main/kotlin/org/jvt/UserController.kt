package org.jvt

import io.javalin.http.Context
import io.javalin.http.NotFoundResponse

data class User(val id: String, val name: String, val email: String, val userDetails: UserDetails?)
data class UserDetails(val dateOfBirth: String, val salary: String)

val users = setOf<User>(
    User(id = "1", name = "John", email = "john@fake.co", userDetails = UserDetails("21.02.1964", "2773 JB")),
    User(id = "2", name = "Mary", email = "mary@fake.co", userDetails = UserDetails("12.05.1994", "1222 JB")),
    User(id = "3", name = "Dave", email = "dave@fake.co", userDetails = UserDetails("01.05.1984", "1833 JB"))
)

object UserController {
    fun getAll(ctx: Context) {
        ctx.json(users.map { it.copy(userDetails = null) }) // remove sensitive information
    }

    fun getOne(ctx: Context) {
        val user = users.find { it.id == ctx.pathParam("user-id") } ?: throw NotFoundResponse()
        ctx.json(user)
    }
}