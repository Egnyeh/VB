package ies.sequeros.application.productos.usecases

import ies.sequeros.domain.repositories.IProductoRepository
import java.util.UUID

class DeleteProductoUseCase(
    private val repository: IProductoRepository
) {
    operator fun invoke(id: UUID): Boolean {
        val producto = repository.findById(id) ?: return false
        repository.delete(producto)
        return true
    }
}