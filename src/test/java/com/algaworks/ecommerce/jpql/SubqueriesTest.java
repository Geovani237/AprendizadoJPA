package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SubqueriesTest extends EntityManagerTest {

    @Test
    public void pesquisarComAny() {
        // Podemos usar o ANY ou SOME

        // Todos os produtos que já foram vendidos por um preco diferente do atual
        String jpql = "select p from Produto p " +
                " where p.preco <> ANY (select precoProduto from ItemPedido where produto = p)";

        // Todos os produtos que já foram vendidos, pelo menos, uma vez pelo preço atual.
//        String jpql = "select p from Produto p " +
//                " where p.preco = ANY (select precoProduto from ItemPedido where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
        List<Produto> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComAll() {
        //Todos os produtos não foram vendidos mais depois que encareceram
        String jpql = "select p from Produto p where " +
                " p.preco > ALL (select precoProduto from ItemPedido where produto = p)";

        //Todos os produtos que sempre foram vendidos pelo preco atual.
//        String jpql = "select p from Produto p where " +
//                " p.preco = ALL (select precoProduto from ItemPedido where produto = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarTodosOsProdutosNaoVendidosComPrecoAtual() {
        String jpql = "select p from Produto p where exists " +
                "(select 1 from ItemPedido i join i.produto p2 where p2.preco <> i.precoProduto and p2 = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj.getId() + " - " + obj.getNome() + " - " + obj.getPreco()));
    }

    @Test
    public void pesquisarClientesComDoisOuMaisPedidos() {
        String jpql = "select c from Cliente c where 2 <=" +
                " (select count(p) from Pedido p where p.cliente = c) ";

        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println(obj.getId() + " - " + obj.getNome() + "Pedido: " + obj.getPedidos().size()));

    }

    @Test
    public void pesquisarComExists() {
        String jpql = "select p from Produto p where exists " +
                " (select 1 from ItemPedido ip2 join ip2.produto p2 where p2 = p)";

        TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

        List<Produto> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComIN() {
        String jpql = "select p from Pedido p where p.id in " +
                " (select p2.id from ItemPedido i2 join i2.pedido p2 join i2.produto pro2 where pro2.preco > 100)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarComINExercicio() {
        String jpql = "select p from Pedido p where p in " +
                " (select ped from ItemPedido ip join ip.pedido ped join ip.produto pro join pro.categorias cat where cat.id = 2)";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId()));
    }

    @Test
    public void pesquisarSubqueries() {

//      Bons clientes. Versão 2
        String jpql = "select c from Cliente c where " +
                " 500 < (select sum(p.total) from Pedido p where p.cliente = c)";

//      Bons clientes. Versão 1.
//        String jpql = "select c from Cliente c where " +
//                " 500 < (select sum(p.total) from c.pedidos p)";

//      Todos os pedido acima da média de venda.
//        String jpql = "select p from Pedido p where " +
//                " p.total > (select avg(total) from Pedido)";

//      O produto ou os produtos mais caros da base.
//        String jpql = "select p from Produto p where " +
//                "p.preco = (select max(preco) from Produto)";


        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);

        List<Cliente> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(obj -> System.out.println("ID: " + obj.getId() + ", Nome: " + obj.getNome()));
    }
}
