package ies.sequeros.application.productos.usecases

import ies.sequeros.application.productos.ProductoDto
import ies.sequeros.domain.repositories.IProductoRepository
import java.util.UUID

class GetProductoUseCase(
    private val repository: IProductoRepository
) {
    operator fun invoke(id: UUID): ProductoDto? {
        val producto = repository.findById(id) ?: return null
        return ProductoDto(
            id = producto.id,
            nombre = producto.nombre,
            descripcion = producto.descripcion,
            precio = producto.precio,
            categoriaId = producto.categoriaId,
            activo = producto.activo
        )
    }
}