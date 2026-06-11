package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger {

    @NotNull
    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id",nullable = false,
            foreignKey = @ForeignKey(name = "fk_nota_fisca_pedido"))
    private Pedido pedido;

    @NotEmpty
    @Lob
    @Column(length = 1000, nullable = false)
    private byte[] xml;

    @PastOrPresent
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;
}
