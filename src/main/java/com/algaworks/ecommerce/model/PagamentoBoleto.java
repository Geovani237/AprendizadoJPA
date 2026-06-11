package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@DiscriminatorValue("boleto")
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

    @NotBlank
    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;

    @FutureOrPresent
    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
}





