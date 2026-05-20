package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Atributo {

    @Column(columnDefinition = "varchar(100) not null")
    private String nome;

    private String valor;
}
