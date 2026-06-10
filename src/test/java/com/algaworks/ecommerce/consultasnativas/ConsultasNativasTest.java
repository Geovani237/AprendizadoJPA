package com.algaworks.ecommerce.consultasnativas;

import com.algaworks.ecommerce.EntityManagerTest;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConsultasNativasTest extends EntityManagerTest {

    @Test
    public void executarSQL() {
        String sql = "select id, nome from produto";

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> lista = query.getResultList();

        lista.forEach(arr -> System.out.printf("Produto => ID: %s, Nome: %s%n", arr[0], arr[1]));
    }

}
