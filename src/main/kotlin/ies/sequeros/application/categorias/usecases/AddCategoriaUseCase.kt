package ies.sequeros.application.categorias.usecases

import ies.sequeros.application.categorias.AddCategoriaCommand
import ies.sequeros.domain.repositories.ICategoriaRepository
import ies.sequeros.application.categorias.CategoriaDto
import ies.sequeros.domain.models.Categoria
import java.util.UUID

class AddCategoriaUseCase(
    private val repository: ICategoriaRepository
) {
    operator fun invoke(command: AddCategoriaCommand): CategoriaDto {
        val nuevaCategoria = Categoria(
            id = UUID.randomUUID(),
            nombre = command.nombre,
            descripcion = command.descripcion,
            activo = command.activo
        )
        repository.create(nuevaCategoria)
        return CategoriaDto(
            id = nuevaCategoria.id,
            nombre = nuevaCategoria.nombre,
            descripcion = nuevaCategoria.descripcion,
            activo = nuevaCategoria.activo
        )
    }
}