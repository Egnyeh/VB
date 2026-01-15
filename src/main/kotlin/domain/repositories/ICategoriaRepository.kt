package ies.sequeros.domain.repositories

import ies.sequeros.domain.models.Categoria
import ies.sequeros.infraestructure.entities.CategoriaJPA
import java.util.UUID

interface ICategoriaRepository {
    fun all(): MutableList<CategoriaJPA>
    fun update(item: Categoria)
    fun create(item: Categoria)
    fun delete(item: Categoria?)
    fun delete(id: UUID)
    fun findById(id: UUID): Categoria
    fun existsById(id: UUID): Categoria
}