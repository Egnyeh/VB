package ies.sequeros.application.categorias.usecases

import ies.sequeros.domain.repositories.ICategoriaRepository
import java.util.UUID

class DeleteCategoriaUseCase(
    private val repository: ICategoriaRepository
) {
    operator fun invoke(id: UUID): Boolean {
        val categoria = repository.findById(id) ?: return false
        repository.delete(categoria)
        return true
    }
}