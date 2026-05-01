package com.ribeiros.sucatas.controller;

import com.ribeiros.sucatas.model.Gasto;
import com.ribeiros.sucatas.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoRepository repository;

    @GetMapping
    public List<Gasto> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Gasto salvar(@RequestBody Gasto gasto) {
        return repository.save(gasto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
