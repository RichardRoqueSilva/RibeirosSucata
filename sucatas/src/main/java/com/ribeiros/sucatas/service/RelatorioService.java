package com.ribeiros.sucatas.service;

import com.ribeiros.sucatas.model.Gasto;
import com.ribeiros.sucatas.model.Transacao;
import com.ribeiros.sucatas.model.dto.DashboardDTO;
import com.ribeiros.sucatas.repository.GastoRepository;
import com.ribeiros.sucatas.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private GastoRepository gastoRepository;

    public DashboardDTO gerarDashboard(LocalDate inicio, LocalDate fim) {
        LocalDateTime inicioDT = inicio.atStartOfDay();
        LocalDateTime fimDT = fim.atTime(23, 59, 59);

        List<Transacao> transacoes = transacaoRepository.findByDataHoraBetween(inicioDT, fimDT);
        List<Gasto> gastos = gastoRepository.findByDataBetween(inicio, fim);

        DashboardDTO dto = new DashboardDTO();
        BigDecimal totalVendido = BigDecimal.ZERO;
        BigDecimal totalComprado = BigDecimal.ZERO;
        Map<String, Double> kgPorMaterial = new HashMap<>();

        for (Transacao t : transacoes) {
            if ("VENDA".equals(t.getTipo())) {
                totalVendido = totalVendido.add(t.getValorTotal());
            } else {
                totalComprado = totalComprado.add(t.getValorTotal());
                // Soma o KG por material para saber quanto "entrou"
                String nomeMat = t.getMaterial().getNome();
                kgPorMaterial.put(nomeMat, kgPorMaterial.getOrDefault(nomeMat, 0.0) + t.getPeso());
            }
        }

        BigDecimal totalGastos = gastos.stream()
                .map(Gasto::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        dto.setTotalVendido(totalVendido);
        dto.setTotalComprado(totalComprado);
        dto.setTotalGastos(totalGastos);
        dto.setKgPorMaterial(kgPorMaterial);

        // Lucro = Vendas - (Compras + Gastos)
        dto.setLucroLiquido(totalVendido.subtract(totalComprado.add(totalGastos)));

        return dto;
    }
}
