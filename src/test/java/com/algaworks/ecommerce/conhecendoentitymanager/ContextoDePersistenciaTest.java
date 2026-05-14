package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    public void usarContextoDePersistencia() {
        entityManager.getTransaction().begin();

        // essa instância de Produto já está em um Contexto de Persistência
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal("100.0")); //Quando pegamos uma instancia já gerenciada como produto, e setamos alguma propriedade igual está sendo feito, isso costuma ser chamado de "Dirty Checking"

        Produto produto2 = new Produto();
        produto2.setNome("Caneca para café");
        produto2.setPreco(new BigDecimal("102.0"));
        produto2.setDescricao("Boa caneca para café");
        // somente após colocar o persist(produto2) o produto2 entrou em um contexto de persistência
        entityManager.persist(produto2);


        Produto produto3 = new Produto();
        produto3.setNome("Caneca para chá");
        produto3.setPreco(new BigDecimal("10.0"));
        produto3.setDescricao("Boa caneca para chá");
        // somente após colocar o merge(produto3) o produto3 entrou em um contexto de persistência
        produto3 = entityManager.merge(produto3);

        entityManager.flush();

        produto3.setDescricao("Alterar descrição");

        entityManager.getTransaction().commit();
    }
}
