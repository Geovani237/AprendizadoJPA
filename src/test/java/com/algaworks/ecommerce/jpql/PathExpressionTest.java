package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PathExpressionTest extends EntityManagerTest {

    @Test
    public void buscarPedidoComProdutoEspecifico() {
        String jpql = "select i.pedido from ItemPedido i where i.id.produtoId = 1";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> pedido = typedQuery.getResultList();
        Assertions.assertEquals(2, pedido.size());

    }

    @Test
    public void usarPathExpressions() {
        String jpql = "select p.cliente.nome from Pedido p";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(l -> System.out.println(l[0]));

    }
}
