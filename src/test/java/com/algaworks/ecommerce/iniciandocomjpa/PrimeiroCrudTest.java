package com.algaworks.ecommerce.iniciandocomjpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    //TODO inserirRegistro
    @Test
    public void inserirRegistro() {
        Cliente cliente = new Cliente();
        cliente.setId(3);
        cliente.setNome("Nadja abmin");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, 3);
        Assertions.assertNotNull(clienteVerificado);
    }

    //TODO buscarPorIdentificado
    @Test
    public void buscarPorIdentificado() {
        Cliente cliente = entityManager.find(Cliente.class, 2);
        System.out.println(cliente.getNome());
        Assertions.assertNotNull(cliente);
    }

    //TODO atualizarRegistro
    @Test
    public void atualizarRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 2);

        cliente.setNome("Geovani Carlos de Souza");

        entityManager.getTransaction().begin();
        entityManager.merge(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertEquals("Geovani Carlos de Souza", clienteVerificado.getNome());
    }

    //TODO removerRegistro
    @Test
    public void removerRegistro() {
        Cliente cliente = entityManager.find(Cliente.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificado = entityManager.find(Cliente.class, 1);
        Assertions.assertNull(clienteVerificado);

    }

}
