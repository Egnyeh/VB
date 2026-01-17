package ies.sequeros.infrastructure.repositories;

import ies.sequeros.domain.models.Categoria;
import ies.sequeros.domain.repositories.ICategoriaRepository;
import ies.sequeros.infrastructure.entities.CategoriaJPA;
import ies.sequeros.infrastructure.mappers.CategoriaMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JPACategoriaRepository implements ICategoriaRepository {
   private final EntityManagerFactory emf;
   public JPACategoriaRepository(final EntityManagerFactory emf) {
      this.emf = emf;
   }
   @Override
   public @NotNull List<@NotNull Categoria> all() {
      EntityManager em = emf.createEntityManager();
      List<CategoriaJPA> items;
      try {
         String jpql = "SELECT c FROM CategoriaJPA c";
         items = em.createQuery(jpql, CategoriaJPA.class).getResultList();
         return items.stream().map(CategoriaMapper::toCategoria).toList();
      } finally {
         em.close();
      }
   }

   @Override
   public void update(final Categoria item) {
      final EntityManager em = this.emf.createEntityManager();
      EntityTransaction tx = em.getTransaction();
      try {
         final CategoriaJPA categoria = CategoriaMapper.toJpa(item);
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
   public void delete(final Categoria item) {
      final EntityManager em = this.emf.createEntityManager();
      EntityTransaction tx = em.getTransaction();
      try {
         tx.begin();
         // asegurar que está en el mismo contexto de persistencia
         final CategoriaJPA elemento = em.merge(CategoriaMapper.toJpa(item));
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
         CategoriaJPA ref = em.getReference(CategoriaJPA.class, id);
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
   public @Nullable Categoria findById(@NotNull UUID id) {
      final EntityManager em = this.emf.createEntityManager();
      try {
         final CategoriaJPA c = em.find(CategoriaJPA.class, id);
         if (c ==  null) {
            return null;
         }
         return CategoriaMapper.toCategoria(c);
      } finally {
         if (em != null && em.isOpen()) {
            em.close();
         }
      }
   }

   @Override
   public boolean existsById(@NotNull String id) {
      EntityManager em = emf.createEntityManager();
      try {
         return em.find(CategoriaJPA.class, id) != null;
      } finally {
         if (em != null && em.isOpen()) {
            em.close();
         }
      }
   }
}