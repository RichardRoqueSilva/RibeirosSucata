package com.ribeiros.sucatas.model;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private BigDecimal precoCompra;

    @Column(nullable = false)
    private BigDecimal precoVenda;

    @Column(nullable = false)
    private Double quantidadeEstoque = 0.0;
}
