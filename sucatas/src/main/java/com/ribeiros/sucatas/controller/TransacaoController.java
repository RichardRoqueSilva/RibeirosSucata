package com.ribeiros.sucatas.controller;

import com.ribeiros.sucatas.model.Transacao;
import com.ribeiros.sucatas.service.CalculadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculadora")
public class TransacaoController {

    @Autowired
    private CalculadoraService service;

    @PostMapping("/processar")
    public Transacao processar(@RequestParam Long materialId,
                               @RequestParam Double peso,
                               @RequestParam String tipo) {
        return service.realizarOperacao(materialId, peso, tipo);
    }
}