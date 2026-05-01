package com.ribeiros.sucatas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Muitas transações para um material
    private Material material;

    private Double peso;
    private BigDecimal valorTotal;

    private String tipo; // "COMPRA" ou "VENDA"
    private LocalDateTime dataHora;
}
