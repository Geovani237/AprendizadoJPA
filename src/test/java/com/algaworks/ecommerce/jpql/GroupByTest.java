package com.algaworks.ecommerce.jpql;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GroupByTest extends EntityManagerTest {

    @Test
    public void agruparResultado() {
        //Quantidade de produtos por categoria.
//        String jpql = "select c.nome, count(p.id) from Categoria c join c.produtos p group by c.id";

        //Total de vendas por mês
//        String jpql = "select concat(year(p.dataCriacao), '/', function('monthname', p.dataCriacao)), sum(p.total) " +
//                " from Pedido p " +
//                " group by year(p.dataCriacao), month(p.dataCriacao) ";


//        Total de vendas por categoria
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.produto pro join pro.categorias c group by c.id";

        //Total de vendas por cliente
//        String jpql = "select c.nome, sum(ip.precoProduto) from ItemPedido ip join ip.pedido p join p.cliente c group by c.nome";

        //Total de vendas por dia e por categoria
        String jpql = "select concat('Data:', function('DATE_FORMAT', p.dataCriacao, '%d/%m/%Y' )), concat('Categoria:',c.nome), concat('Total Vendas: R$',sum(i.precoProduto)) from ItemPedido i join i.produto p join p.categorias c group by p.dataCriacao, c.nome";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assertions.assertFalse(lista.isEmpty());

        lista.forEach(arr -> System.out.println(arr[0] + ", " + arr[1] + ", " + arr[2]));
    }
}
