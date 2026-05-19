package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DiscriminatorValue("cartao")
@Entity
//@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

//    @EqualsAndHashCode.Include
//    @Id
//    @Column(name = "pedido_id")
//    private Integer id;

//    @MapsId
//    @OneToOne(optional = false)
//    @JoinColumn(name = "pedido_id")
//    private Pedido pedido;
//
//    @Enumerated(EnumType.STRING)
//    private StatusPagamento status;

    @Column(name = "numero_cartao")
    private String numeroCartao;
}
