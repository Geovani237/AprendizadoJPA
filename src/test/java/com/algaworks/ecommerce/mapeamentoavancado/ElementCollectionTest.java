package com.algaworks.ecommerce.mapeamentoavancado;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setTags(Arrays.asList("ebook", "livro-digital"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());

        Assertions.assertFalse(produtoVerificado.getTags().isEmpty());
    }

    @Test
    public void aplicarAtributos() {
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setAtributos(Arrays.asList(new Atributo("tela", "320x600")));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
        Assertions.assertFalse(produtoVerificado.getAtributos().isEmpty());
    }
}
