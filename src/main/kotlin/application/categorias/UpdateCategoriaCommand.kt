@file:UseContextualSerialization(UUID::class)

package application.categorias

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.util.UUID

@Serializable
data class UpdateCategoriaCommand(
    val id: UUID,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
)
