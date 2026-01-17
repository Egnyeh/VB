package ies.sequeros.application.productos.usecases

import ies.sequeros.application.productos.AddProductoCommand
import ies.sequeros.domain.repositories.IProductoRepository
import ies.sequeros.application.productos.ProductoDto
import ies.sequeros.domain.models.Producto
import java.util.UUID

class AddProductoUseCase(
    private val repository: IProductoRepository
) {
    operator fun invoke(command: AddProductoCommand): ProductoDto {
        val nuevoProducto = Producto(
            id = UUID.randomUUID(),
            nombre = command.nombre,
            descripcion = command.descripcion,
            categoriaId = command.categoriaId,
            precio = command.precio,
            activo = command.activo
        )
        repository.create(nuevoProducto)
        return ProductoDto(
            id = nuevoProducto.id,
            nombre = nuevoProducto.nombre,
            descripcion = nuevoProducto.descripcion,            
            precio = nuevoProducto.precio,
            categoriaId = nuevoProducto.categoriaId,
            activo = nuevoProducto.activo
        )
    }
}