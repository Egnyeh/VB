package ies.sequeros.domain.repositories

import ies.sequeros.domain.models.Categoria
import java.util.UUID

interface ICategoriaRepository {
    fun update(item: Categoria)
    fun create(item: Categoria)
    fun delete(item: Categoria?)
    fun delete(id: UUID)
}