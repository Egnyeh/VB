package ies.sequeros.application.categorias.usecases

import ies.sequeros.application.categorias.CategoriaDto
import ies.sequeros.application.categorias.UpdateCategoriaCommand
import ies.sequeros.domain.models.Categoria
import ies.sequeros.domain.repositories.ICategoriaRepository

class UpdateCategoriaUseCase(
    private val repository: ICategoriaRepository
) {
    operator fun invoke(command: UpdateCategoriaCommand): CategoriaDto? {
        repository.findById(command.id) ?: return null

        val updatedCategoria = Categoria(
            id = command.id,
            nombre = command.nombre,
            descripcion = command.descripcion,
            activo = command.activo
        )

        repository.update(updatedCategoria)

        return CategoriaDto(
            id = updatedCategoria.id,
            nombre = updatedCategoria.nombre,
            descripcion = updatedCategoria.descripcion,
            activo = updatedCategoria.activo
        )
    }
}