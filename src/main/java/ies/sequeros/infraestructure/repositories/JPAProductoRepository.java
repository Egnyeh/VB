package ies.sequeros.infraestructure.repositories;

package ies.sequeros.infraestructure.repositories;


import ies.sequeros.domain.models.Producto;
import ies.sequeros.domain.repositories.IProductoRepository;
import ies.sequeros.infraestructure.entities.CategoriaJPA;
import ies.sequeros.infraestructure.entities.ProductoJPA;
import ies.sequeros.infraestructure.mappers.CategoriaMapper;
import ies.sequeros.infraestructure.mappers.ProductMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class JPAProductoRepository implements IProductoRepository {
    private final EntityManagerFactory emf;
    public JPAProductoRepository(final EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public @NotNull List<@NotNull ProductoJPA> all() {
        EntityManager em = emf.createEntityManager();
        List<ProductoJPA> items;
        try {
            String jpql = "SELECT p FROM ProductoJPA p";
            items = em.createQuery(jpql,
                    ProductoJPA.class
            ).getResultList();
            return 
                items.stream().map(ProductMapper::toProducto).toList();
        } finally {
            em.close();

        }
   @Override
    public void create(@NotNull Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            ProductoJPA producto = ProductMapper.toJpa(item);
            tx.begin();
            em.persist(producto);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public void update(final Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            final ProductoJPA producto =
                    ProductMapper.toJpa(item);
            tx.begin();
            em.merge(producto);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public void delete(final Producto item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//asegurar que está en el mismo contexto de persistencia
            final Producto elemento = em.merge(item);
            em.remove(elemento);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public void delete(final UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            //solo se obtiene la referencia
            ProductoJPA ref = em.getReference(ProductoJPA.class,
                    id);
            em.remove(ref);
            tx.commit();
        } catch (final RuntimeException ex) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public @Nullable Producto existsById(@Nullable UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            final ProductoJPA c = em.find(ProductoJPA.class, id);
            if (c == null) {
                return null;
            }
            // Forzar carga de la colección dentro de la sesión
            //c.getProductos().size();
            return ProductMapper.toProducto(c);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public @Nullable Producto findById(@Nullable UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            final ProductoJPA c = em.find(ProductoJPA.class, id);
            if (c == null) {
                return null;
            }
            return ProductMapper.toProducto(c);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


}
