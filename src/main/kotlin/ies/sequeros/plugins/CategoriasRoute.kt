package ies.sequeros.plugins

import jakarta.persistence.EntityManagerFactory
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.configureCategoriasRoutes() {
    route("/categorias") {
        get {
            try {
                val emf by inject<EntityManagerFactory>()
                call.respondText("EMF inyectado: ${emf.isOpen}", status = HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respondText("Error EMF: ${e.message}\n${e.stackTraceToString()}", status = HttpStatusCode.InternalServerError)
            }
        }
    }
}