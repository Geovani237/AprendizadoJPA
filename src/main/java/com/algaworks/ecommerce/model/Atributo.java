package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(columnDefinition = "varchar(100) not null")
    private String nome;

    @NotBlank
    private String valor;
}
