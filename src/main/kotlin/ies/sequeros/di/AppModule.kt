package ies.sequeros.di

import ies.sequeros.application.categorias.usecases.*
import ies.sequeros.application.productos.usecases.*
import ies.sequeros.domain.repositories.ICategoriaRepository
import ies.sequeros.domain.repositories.IProductoRepository
import ies.sequeros.infrastructure.repositories.JPACategoriaRepository  
import ies.sequeros.infrastructure.repositories.JPAProductoRepository
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.koin.dsl.module

val appModulo = module {
    // EntityManagerFactory - Singleton
    single<EntityManagerFactory> {
        Persistence.createEntityManagerFactory("UnidadPersistencia")
    }

    // Repositorios - Singleton
    single<ICategoriaRepository> { JPACategoriaRepository(get()) }
    single<IProductoRepository> { JPAProductoRepository(get()) }

    // Casos de Uso de Categorías - Factory
    factory { GetAllCategoriaUseCase(get()) }
    factory { GetCategoriaUseCase(get()) }
    factory { AddCategoriaUseCase(get()) }
    factory { UpdateCategoriaUseCase(get()) }
    factory { DeleteCategoriaUseCase(get()) }

    // Casos de Uso de Productos - Factory
    factory { GetAllProductoUseCase(get()) }
    factory { GetProductoUseCase(get()) }
    factory { AddProductoUseCase(get()) }
    factory { UpdateProductoUseCase(get()) }
    factory { DeleteProductoUseCase(get()) }
}
