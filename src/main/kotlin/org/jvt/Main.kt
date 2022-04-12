package org.jvt

import io.javalin.Javalin
import io.javalin.plugin.json.jsonMapper
import io.javalin.plugin.rendering.vue.VueComponent

fun main() {
    var todos = arrayOf(Todo(123123123, "My very first todo", false))

    val app = Javalin.create { config ->
        config.enableWebjars()
    }.start(7070)


    app.error(404, "html", VueComponent("not-found"))
    app.error(500, "html", VueComponent("err500"))

    app.get("/", VueComponent("Home"))

    //Genres
    app.get("/genres", VueComponent("genre-overview"))
    app.get("/genres/{genre-id}", VueComponent("genre-profile"))
    app.get("/api/genres/{g}") { ctx ->
        MovieDB().use { db ->
            //ctx.contentType("application/json")
            ctx.result(db.getGenres(ctx.pathParam("g")))
        }
    }

    app.get("/titles", VueComponent("x"))
    app.get("/titles/{title-name}", VueComponent("title-profile"))
    app.get("/api/titles") { ctx ->
        MovieDB().use { db ->
            ctx.contentType("application/json")
            ctx.json(db.titles) // See x.vue!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        }
    }

    app.get("/api/fbg/{fbg}") { ctx ->
        MovieDB().use { db ->
            ctx.result(db.getFilmsByDirector(ctx.pathParam("fbg")))
        }
    }



    app.get("/users", VueComponent("user-overview"))
    app.get("/users/{user-id}", VueComponent("user-profile"))
    app.get("/api/users", UserController::getAll)
    app.get("/api/users/{user-id}", UserController::getOne)

    app.get("/movies", VueComponent("movie-overview"))
    app.get("/movies/{movie-id}", VueComponent("movie-profile"))
    app.get("/api/movies", MovieController::getAll)
    app.get("/api/movies/{movie-id}", MovieController::getOne)

    app.get("/mi") { ctx ->
        MovieDB().use { db ->
            val d : String = db.x()
            ctx.result(d)
        }
    }



    app.get("/api/entries") { ctx ->
        MovieDB().use { db ->
            ctx.json(db.numberOfEntries.toString())
        }
    }


















    app.get("/todos") { ctx -> ctx.json(todos) }

    app.put("/todos") { ctx ->
        todos = ctx.bodyAsClass<Array<Todo>>()
        ctx.status(204)
    }
}

data class Todo(val id: Long = -1, val title: String = "", val completed: Boolean = false)

/*
    app.get("/") { ctx ->
        ctx.result("hello")
        VueComponent("hello-world")
        ctx.status(404)
        //VueComponent("not-found")
    }
*/
