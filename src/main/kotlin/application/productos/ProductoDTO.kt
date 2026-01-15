@file:UseContextualSerialization(UUID::class, BigDecimal::class) //Esto le dice a kotlinx.serrialization que use el UUIDSerializer

package application.productos

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.math.BigDecimal
import java.util.UUID

@Serializable
data class CategoriaDto( //Incluye los datos necesarios para la API
    val id: UUID?,
    val nombre: String,
    val descripcion: String,
    val categoriaId: UUID,
    val precio: BigDecimal,
    val activo: Boolean
)
