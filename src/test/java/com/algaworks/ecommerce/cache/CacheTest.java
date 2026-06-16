package com.algaworks.ecommerce.cache;

import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.Cache;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheTest {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    public static void setUpBeforeClass() {
        entityManagerFactory = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        entityManagerFactory.close();
    }

    @Test
    public void removerDoCache() {
        Cache cache = entityManagerFactory.getCache();
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1: ");
        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        System.out.println("Removendo do cache");
        cache.evictAll();
//        cache.evict(Pedido.class);
//        cache.evict(Pedido.class, 1);

        System.out.println("Buscando a partir da instância 2: ");
        entityManager2.find(Pedido.class, 1);
        entityManager2.find(Pedido.class, 2);
    }

    @Test
    public void adicionarPedidosNoCach() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1: ");
        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        System.out.println("Buscando a partir da instância 2: ");
        entityManager2.find(Pedido.class, 1);
    }

    @Test
    public void buscarDoCache() {
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1: ");
        entityManager1.find(Pedido.class, 1);

        System.out.println("Buscando a partir da instância 2: ");
        entityManager2.find(Pedido.class, 1);
    }


}
