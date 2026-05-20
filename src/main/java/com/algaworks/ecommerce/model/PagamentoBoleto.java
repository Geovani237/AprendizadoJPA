package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("boleto")
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

//    @EqualsAndHashCode.Include
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

//    @Column(name = "pedido_id")
//    private Integer pedidoId;
//
//    @Enumerated(EnumType.STRING)
//    private StatusPagamento status;

    @Column(name = "codigo_barras")
    private String codigoBarras;
}





