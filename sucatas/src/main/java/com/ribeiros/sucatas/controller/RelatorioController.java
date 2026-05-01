package com.ribeiros.sucatas.controller;

import com.ribeiros.sucatas.model.dto.DashboardDTO;
import com.ribeiros.sucatas.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/analise")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping
    public DashboardDTO obterDados(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return service.gerarDashboard(inicio, fim);
    }
}
