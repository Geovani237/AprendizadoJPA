package com.algaworks.ecommerce.relacionamentos;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutoRelacionamentosTest extends EntityManagerTest {

    @Test
    public void verificarRelacionamento() {
        Categoria categoriaPai = new Categoria();
        categoriaPai.setNome("Eletrõnicos");

        Categoria categoria = new Categoria();
        categoria.setNome("Celulares");
        categoria.setCategoraiPai(categoriaPai);

        entityManager.getTransaction().begin();
        entityManager.persist(categoriaPai);
        entityManager.persist(categoria);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificada = entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaVerificada.getCategoraiPai());

        Categoria categoriaPaiVerificada = entityManager.find(Categoria.class, categoriaPai.getId());
        Assertions.assertFalse(categoriaPaiVerificada.getCategorias().isEmpty());

    }
}
