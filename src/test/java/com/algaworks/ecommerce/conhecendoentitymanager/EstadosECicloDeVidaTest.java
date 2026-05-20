package com.algaworks.ecommerce.conhecendoentitymanager;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;
import org.junit.jupiter.api.Test;

public class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    public void analisarEstados() {
        //Estado transiente ou novo
        Categoria categoriaNovo = new Categoria();
        categoriaNovo.setNome("Eletrônicos");

        //Estado gerenciado
        Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1);
        categoriaGerenciada.setNome("Eletrônicos");
        //Estado remove
        entityManager.remove(categoriaGerenciada);

        //voltando para o estado gerenciado
        entityManager.persist(categoriaGerenciada);

        //desanexar
        entityManager.detach(categoriaGerenciada);

    }
}
