package ies.sequeros.application.productos.usecases

import ies.sequeros.application.productos.ProductoDto
import ies.sequeros.application.productos.UpdateProductoCommand
import ies.sequeros.domain.models.Producto
import ies.sequeros.domain.repositories.IProductoRepository

class UpdateProductoUseCase(
    private val repository: IProductoRepository
) {
    operator fun invoke(command: UpdateProductoCommand): ProductoDto? {
        repository.findById(command.id) ?: return null

        val updatedProducto = Producto(
            id = command.id,
            nombre = command.nombre,
            descripcion = command.descripcion,
            precio = command.precio,
            categoriaId = command.categoriaId,
            activo = command.activo
        )

        repository.update(updatedProducto)

        return ProductoDto(
            id = updatedProducto.id,
            nombre = updatedProducto.nombre,
            descripcion = updatedProducto.descripcion,
            precio = updatedProducto.precio,
            categoriaId = updatedProducto.categoriaId,
            activo = updatedProducto.activo
        )
    }
}