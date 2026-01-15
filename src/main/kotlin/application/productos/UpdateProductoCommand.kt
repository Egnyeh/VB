@file:UseContextualSerialization(UUID::class, BigDecimal::class)

package application.productos

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.math.BigDecimal
import java.util.UUID

@Serializable
data class UpdateProductoCommand(
    val id: UUID,
    val nombre: String,
    val descripcion: String,
    val categoriaId: UUID,
    val precio: BigDecimal,
    val activo: Boolean
)