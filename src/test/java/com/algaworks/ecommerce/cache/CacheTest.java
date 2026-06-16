package com.algaworks.ecommerce.cache;

import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

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
    public void controlarCacheDinamicamente() {
//      jakarta.persistence.cache.retrieveMode -> vou fazer uma pesquisar, eu quero usar o cache ou não?
//      jakarta.persistence.cache.storeMode -> vou fazer uma pesquisar, eu quero armazenar o resultado no cache ou não?
        Cache cache = entityManagerFactory.getCache();

        System.out.println("Buscando todos os pedidos..............................");
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.setProperty("jakarta.persistence.cache.storeMode", CacheStoreMode.BYPASS);
        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .setHint("jakarta.persistence.cache.storeMode", CacheStoreMode.USE)
                .getResultList();

        System.out.println("Buscando o pedido de Id igual a 2.......................");
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        Map<String, Object> propriedades = new HashMap<>();
//        propriedades.put("jakarta.persistence.cache.storeMode", CacheStoreMode.BYPASS);
//        propriedades.put("jakarta.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        entityManager2.find(Pedido.class, 2, propriedades);


        System.out.println("Buscando todos os pedidos (de novo)......................");
        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        entityManager3
                .createQuery("select p from Pedido p", Pedido.class)
//                .setHint("jakarta.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
                .getResultList();
    }

    @Test
    public void analisarOpcoesCache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1: ");
        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        Assertions.assertTrue(cache.contains(Pedido.class, 1));
    }

    @Test
    public void verificarSeEstaNoCache() {
        Cache cache = entityManagerFactory.getCache();

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();

        System.out.println("Buscando a partir da instância 1: ");
        entityManager1
                .createQuery("select p from Pedido p", Pedido.class)
                .getResultList();

        Assertions.assertTrue(cache.contains(Pedido.class, 1));
        Assertions.assertTrue(cache.contains(Pedido.class, 2));
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
