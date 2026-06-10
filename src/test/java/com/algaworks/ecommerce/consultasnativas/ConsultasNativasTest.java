package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConsultasNativasTest extends EntityManagerTest {

    @Test
    public void executarSQlRetornandoEntidade() {
        // Contem todos os campos da entidade Produto porem em outra tabela, e os nomes da tabela produto_loja tem de ser exatamente iguais aos de produto
//        String sql = "select id, nome, descricao, data_criacao, data_ultima_atualizacao, preco, fotos " +
//                " from produto_loja";

        // Contem todos os campos porem com nomes diferentes, sendo assim tem de dar um alias para os nomes corretos da entidade Produto
//        String sql = "select prd_id id, prd_nome nome, prd_descricao descricao, prd_data_criacao data_criacao, prd_data_ultima_atualizacao data_ultima_atualizacao, prd_preco preco, prd_foto fotos " +
//                " from ecm_produto";

        // Não contem todos os campos da entitada sendo assim temo de colocar um null antes dos campos que não existem
        String sql = "select id, nome, descricao, " +
                " null data_criacao, null data_ultima_atualizacao, " +
                " preco, null fotos " +
                " from erp_produto";


        Query query = entityManager.createNativeQuery(sql, Produto.class);

        List<Produto> lista = query.getResultList();

        lista.forEach(p -> System.out.printf("Produto => ID: %s, Nome: %s%n", p.getId(), p.getNome()));
    }

    @Test
    public void executarSQL() {
        String sql = "select id, nome from produto";

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.forEach(arr -> System.out.printf("Produto => ID: %s, Nome: %s%n", arr[0], arr[1]));
    }

}
