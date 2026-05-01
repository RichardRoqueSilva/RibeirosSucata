package com.ribeiros.sucatas.controller;

import com.ribeiros.sucatas.model.Material;
import com.ribeiros.sucatas.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiais")
public class MaterialController {

    @Autowired
    private MaterialRepository repository;

    @GetMapping
    public List<Material> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Material salvar(@RequestBody Material material) {
        return repository.save(material);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
