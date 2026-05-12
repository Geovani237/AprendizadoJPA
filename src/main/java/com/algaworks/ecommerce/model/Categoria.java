package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categoria")
public class Categoria {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
    @TableGenerator(name = "tabela", table = "hibernate_sequences",
            pkColumnName = "sequence_name", pkColumnValue = "categoria",
            valueColumnName = "next_val", initialValue = 0, allocationSize = 50)
    private Integer id;

    private String nome;

    @Column(name = "categoria_pai_id")
    private Integer categoraiPaiId;
}
