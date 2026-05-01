package com.ribeiros.sucatas.model.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Map;

@Data
public class DashboardDTO {
    private BigDecimal totalVendido;
    private BigDecimal totalComprado;
    private BigDecimal totalGastos;
    private BigDecimal lucroLiquido;
    private Map<String, Double> kgPorMaterial; // Nome do Material -> Quantidade em KG
}
