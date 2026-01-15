@file:UseContextualSerialization(UUID::class, BigDecimal::class)

package application.productos

import kotlinx.serialization.UseContextualSerialization
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.util.UUID

@Serializable
data class AddProductoCommand(
    val nombre: String,
    val descripcion: String,
    val categoriaId: UUID,
    val precio: BigDecimal,
    val activo: Boolean = false
)