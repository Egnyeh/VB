package ies.sequeros.application.categorias.usecases

import ies.sequeros.application.categorias.CategoriaDto
import ies.sequeros.domain.repositories.ICategoriaRepository

class GetAllCategoriaUseCase(
    private val repository: ICategoriaRepository
) {
    operator fun invoke(): List<CategoriaDto> {
        return repository.all().map { categoria ->
            CategoriaDto(
                id = categoria.id,
                nombre = categoria.nombre,
                descripcion = categoria.descripcion,
                activo = categoria.activo
            )
        }
    }
}