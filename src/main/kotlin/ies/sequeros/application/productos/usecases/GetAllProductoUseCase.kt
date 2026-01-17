package ies.sequeros.application.productos.usecases

import ies.sequeros.application.productos.ProductoDto
import ies.sequeros.domain.repositories.IProductoRepository

class GetAllProductoUseCase(
    private val repository: IProductoRepository
) {
    operator fun invoke(): List<ProductoDto> {
        return repository.all().map { producto ->
            ProductoDto(
                id = producto.id,
                nombre = producto.nombre,
                descripcion = producto.descripcion,
                precio = producto.precio,
                categoriaId = producto.categoriaId,
                activo = producto.activo
            )
        }
    }
}