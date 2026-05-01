package com.ribeiros.sucatas.service;

import com.ribeiros.sucatas.model.Material;
import com.ribeiros.sucatas.model.Transacao;
import com.ribeiros.sucatas.repository.MaterialRepository;
import com.ribeiros.sucatas.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CalculadoraService {

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Transactional // Garante que se der erro em um, não salva o outro
    public Transacao realizarOperacao(Long materialId, Double peso, String tipo) {
        // 1. Busca o material
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        // 2. Calcula o valor total baseado no preço do material
        java.math.BigDecimal valorTotal;
        if (tipo.equalsIgnoreCase("COMPRA")) {
            valorTotal = material.getPrecoCompra().multiply(java.math.BigDecimal.valueOf(peso));
            material.setQuantidadeEstoque(material.getQuantidadeEstoque() + peso);
        } else {
            valorTotal = material.getPrecoVenda().multiply(java.math.BigDecimal.valueOf(peso));
            material.setQuantidadeEstoque(material.getQuantidadeEstoque() - peso);
        }

        // 3. Salva a transação
        Transacao t = new Transacao();
        t.setMaterial(material);
        t.setPeso(peso);
        t.setValorTotal(valorTotal);
        t.setTipo(tipo.toUpperCase());
        t.setDataHora(LocalDateTime.now());

        // 4. Atualiza o material com o novo estoque
        materialRepository.save(material);

        return transacaoRepository.save(t);
    }
}