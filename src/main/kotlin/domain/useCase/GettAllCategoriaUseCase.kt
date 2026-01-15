package ies.sequeros.domain.useCase

import ies.sequeros.infraestructure.entities.CategoriaJPA
import ies.sequeros.domain.repositories.ICategoriaRepository

class GetAllCategoriaUseCase(
    private val repository: ICategoriaRepository
) {
    operator fun invoke(): List<CategoriaJPA> {

        // Sevuelve lo que hay en la base de datos
        return repository.all()
    }
}
