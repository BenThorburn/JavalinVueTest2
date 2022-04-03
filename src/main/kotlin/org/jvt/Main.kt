package org.jvt

import io.javalin.Javalin
import io.javalin.plugin.rendering.vue.VueComponent

fun main() {
    var todos = arrayOf(Todo(123123123, "My very first todo", false))
    val app = Javalin.create { config ->
        config.enableWebjars()
    }.start(7070)

    app.error(404, "html", VueComponent("not-found"))

    app.get("/", VueComponent("Home"))

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
