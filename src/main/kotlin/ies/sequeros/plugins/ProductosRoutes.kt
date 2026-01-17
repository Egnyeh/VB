package ies.sequeros.plugins

import ies.sequeros.application.productos.AddProductoCommand
import ies.sequeros.application.productos.UpdateProductoCommand
import ies.sequeros.application.productos.usecases.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.UUID

fun Route.configureProductosRoutes() {
    route("/productos") {
        
        // GET /productos - Obtener todos los productos
        get {
            val useCase by inject<GetAllProductoUseCase>()
            val items = useCase()
            call.respond(HttpStatusCode.OK, items)
        }

        // POST /productos - Crear nuevo producto
        post {
            val useCase by inject<AddProductoUseCase>()
            val command = call.receive<AddProductoCommand>()
            val item = useCase(command)
            
            val location = "${call.request.uri}/${item.id}"
            call.response.header(HttpHeaders.Location, location)
            
            call.respond(HttpStatusCode.Created, item)
        }

        // GET /productos/{id} - Obtener un producto por ID
        get("/{id}") {
            val useCase by inject<GetProductoUseCase>()
            
            val stringId = call.parameters["id"] 
                ?: return@get call.respond(
                    HttpStatusCode.BadRequest, 
                    mapOf("error" to "El parámetro id es obligatorio")
                )
            
            val id = try {
                UUID.fromString(stringId)
            } catch (e: IllegalArgumentException) {
                return@get call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El id debe ser un UUID válido")
                )
            }
            
            val producto = useCase(id)
            if (producto == null) {
                call.respond(
                    HttpStatusCode.NotFound,
                    mapOf("error" to "Producto no encontrado")
                )
            } else {
                call.respond(HttpStatusCode.OK, producto)
            }
        }

        // PUT /productos/{id} - Actualizar un producto
        put("/{id}") {
            val useCase by inject<UpdateProductoUseCase>()
            
            val stringId = call.parameters["id"]
                ?: return@put call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El parámetro id es obligatorio")
                )
            
            val id = try {
                UUID.fromString(stringId)
            } catch (e: IllegalArgumentException) {
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El id debe ser un UUID válido")
                )
            }
            
            val command = call.receive<UpdateProductoCommand>()
            
            if (command.id != id) {
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El id del path no coincide con el id del body")
                )
            }
            
            val productoActualizado = useCase(command)
            if (productoActualizado == null) {
                call.respond(
                    HttpStatusCode.NotFound,
                    mapOf("error" to "Producto no encontrado")
                )
            } else {
                call.respond(HttpStatusCode.OK, productoActualizado)
            }
        }

        // DELETE /productos/{id} - Eliminar un producto
        delete("/{id}") {
            val useCase by inject<DeleteProductoUseCase>()
            
            val stringId = call.parameters["id"]
                ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El parámetro id es obligatorio")
                )
            
            val id = try {
                UUID.fromString(stringId)
            } catch (e: IllegalArgumentException) {
                return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    mapOf("error" to "El id debe ser un UUID válido")
                )
            }
            
            val eliminado = useCase(id)
            if (eliminado) {
                call.respond(
                    HttpStatusCode.OK,
                    mapOf("message" to "Producto eliminado correctamente")
                )
            } else {
                call.respond(
                    HttpStatusCode.NotFound,
                    mapOf("error" to "Producto no encontrado")
                )
            }
        }
    }
}