package com.algaworks.ecommerce.criteria;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BasicoCriteriaTest extends EntityManagerTest {

    @Test
    public void selecionarUmAtributoParaRetorno(){
        // Aqui a descrição mais adequada seria a seguinte:
        // Declaro a variável criteriaBuilder, que recebe o retorno do metodo getCriteriaBuilder invocado a partir da variável entityManager
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // A classe CriteriaQuery é quem vai receber as instruções pra montar a consulta.
        // Nesse momento não temos nada pra equiparar com uma consulta SQL ainda. Apenas criamos as classes necessárias
        CriteriaQuery<BigDecimal> criteriaQuery = criteriaBuilder.createQuery(BigDecimal.class);

        // Aqui, já temos algo equivalente ao seguinte: "from pedido"
        // Pensando em uma consulta SQL nativa, isso não rodaria, e aqui também não, pois precisamos no mínimo indicar quais campos queremos selecionar
        Root<Pedido> root = criteriaQuery.from(Pedido.class);


        // Aqui indicamos os campos que queremos selecionar. A classe Root mantém uma referência a tabela raiz da consulta, declarada na cláusula "from". Nesse caso, a tabela pedido
        // Nesse momento, temos uma consulta equiparada a: "select total from pedido"
        // Agora a consulta já poderia ser executada
        criteriaQuery.select(root.get("total"));

        // Pra retornar todos os campos (*), seria dessa forma:
        // criteriaQuery.select(root);
        // Assim a consulta seria "select * from pedido"

        // Se precisamos filtrar os dados, adicionamos a cláusula "where"
        // Aqui a consulta está equiparada a "select total from pedido where id = 1"
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),1));

        // A classe CriteriaQuery apenas define a consulta. A execução fica a cargo de Query, nesse caso usamos a especialização TypedQuery que permite definir o tipo do retorno
        TypedQuery<BigDecimal> typedQuery = entityManager.createQuery(criteriaQuery);


        // Executamos a consulta e na verdade não estamos "garantindo" que é um resultado único. Estamos dizendo que esperamos por um resultado único. Se a consulta resultar em mais de um, uma exceção será lançada
        BigDecimal total = typedQuery.getSingleResult();

        //Validando pelo Assertions se o resultado retornado é igual ao parametro informado.
        Assertions.assertEquals(new BigDecimal("2398.00"), total);
    }

    @Test
    public void buscarPorIdentificador() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteriaQuery = criteriaBuilder.createQuery(Pedido.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.equal(root.get("id"), 1));

//        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> typedQuery = entityManager
//                .createQuery(jpql, Pedido.class);
                .createQuery(criteriaQuery);

        Pedido pedido = typedQuery.getSingleResult();
        Assertions.assertNotNull(pedido);
    }
}
