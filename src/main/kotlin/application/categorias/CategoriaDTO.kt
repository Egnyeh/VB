@file:UseContextualSerialization(UUID::class) //Esto le dice a kotlinx.serrialization que use el UUIDSerializer

package ies.sequeros.dam.ad.orm.application.categorias

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import java.util.UUID

@Serializable
data class CategoriaDto( //Incluye los datos necesarios para la API
    val id: UUID?,
    val nombre: String,
    val descripcion: String,
    val activo: Boolean
)

