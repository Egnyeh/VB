package ies.sequeros.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    // Configurar manejo de excepciones CON DETALLES
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            // Imprimir en consola
            this@configureRouting.log.error("Error en la aplicación", cause)
            
            // Responder con detalles
            call.respondText(
                text = "500: ${cause.message}\n\nStacktrace:\n${cause.stackTraceToString()}",
                status = HttpStatusCode.InternalServerError
            )
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        configureCategoriasRoutes()
        configureProductosRoutes()
    }
}