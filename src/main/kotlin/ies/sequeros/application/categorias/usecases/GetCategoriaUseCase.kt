package ies.sequeros.application.categorias.usecases

import ies.sequeros.application.categorias.CategoriaDto
import ies.sequeros.domain.repositories.ICategoriaRepository
import java.util.UUID

class GetCategoriaUseCase(
    private val repository: ICategoriaRepository
) {
    operator fun invoke(id: UUID): CategoriaDto? {
        val categoria = repository.findById(id) ?: return null
        return CategoriaDto(
            id = categoria.id,
            nombre = categoria.nombre,
            descripcion = categoria.descripcion,
            activo = categoria.activo
        )
    }
}