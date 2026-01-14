package ies.sequeros.domain.repositories

import ies.sequeros.domain.models.Producto
import java.util.UUID

interface IProductoRepository {
    fun all(): MutableList<Producto>
    fun update(item: Producto)
    fun create(item: Producto)
    fun delete(item: Producto?)
    fun delete(id: UUID)
    fun findById(id: UUID): Producto?
    fun existsById(id: UUID): Boolean
}