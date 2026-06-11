package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "estoque")
public class Estoque extends EntidadeBaseInteger {

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_estoque_produto"))
    private Produto produto;

    @PositiveOrZero
    @NotNull
    @Column(nullable = false)
    private Integer quantidade;
}
