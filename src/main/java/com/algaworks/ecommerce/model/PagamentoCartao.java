package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DiscriminatorValue("cartao")
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    @NotEmpty
    @Column(name = "numero_cartao", length = 50)
    private String numeroCartao;
}
