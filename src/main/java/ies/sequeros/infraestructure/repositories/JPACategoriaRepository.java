package ies.sequeros.infraestructure.repositories;


import ies.sequeros.domain.models.Categoria;
import ies.sequeros.domain.repositories.ICategoriaRepository;
import ies.sequeros.infraestructure.entities.CategoriaJPA;
import ies.sequeros.infraestructure.mappers.CategoriaMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class JPACategoriaRepository implements ICategoriaRepository {
    private final EntityManagerFactory emf;
    public JPACategoriaRepository(final EntityManagerFactory emf) {
        this.emf = emf;
    }
    @Override
    public @NotNull List<@NotNull CategoriaJPA> all() {
        EntityManager em = emf.createEntityManager();
        List<CategoriaJPA> items;
        try {
            String jpql = "SELECT c FROM CategoriaJPA c";
            items = em.createQuery(jpql,
                    CategoriaJPA.class
            ).getResultList();
            return
                    items.stream().map(CategoriaMapper::toCategoria).toList();
        } finally {
            em.close();
        }
    }
    @Override
    public void create(@NotNull Categoria item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            CategoriaJPA categoria = CategoriaMapper.toJpa(item);
            tx.begin();
            em.persist(categoria);
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
    public void update(final Categoria item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            final CategoriaJPA categoria =
                    CategoriaMapper.toJpa(item);
            tx.begin();
            em.merge(categoria);
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
    public void delete(final Categoria item) {
        final EntityManager em = this.emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
//asegurar que está en el mismo contexto de persistencia
            final Categoria elemento = em.merge(item);
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
            CategoriaJPA ref = em.getReference(CategoriaJPA.class,
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
    public @Nullable Categoria existsById(@Nullable UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            final CategoriaJPA c = em.find(CategoriaJPA.class, id);
            if (c == null) {
                return null;
            }
            // Forzar carga de la colección dentro de la sesión
            //c.getProductos().size();
            return CategoriaMapper.toCategoria(c);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    @Override
    public @Nullable Categoria findById(@Nullable UUID id) {
        final EntityManager em = this.emf.createEntityManager();
        try {
            final CategoriaJPA c = em.find(CategoriaJPA.class, id);
            if (c == null) {
                return null;
            }
            return CategoriaMapper.toCategoria(c);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }


}



