package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "categoria", uniqueConstraints = {@UniqueConstraint(name = "unq_name", columnNames = {"nome"})})
public class Categoria extends EntidadeBaseInteger {

    @Column(length = 100, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "categoria_pai_id", foreignKey = @ForeignKey(name = "fk_categoria_categoria_pai"))
    private Categoria categoraiPai;

    @OneToMany(mappedBy = "categoraiPai")
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
}
