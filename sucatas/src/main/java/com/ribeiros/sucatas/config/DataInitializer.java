package com.ribeiros.sucatas.config;

import com.ribeiros.sucatas.model.Material;
import com.ribeiros.sucatas.model.Usuario;
import com.ribeiros.sucatas.repository.MaterialRepository;
import com.ribeiros.sucatas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // 1. Criar Usuário Admin se não existir
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Senha inicial
            usuarioRepository.save(admin);
            System.out.println(">>> Usuário padrão 'admin' criado com sucesso!");
        }

        // 2. Criar Materiais iniciais se não existir nenhum
        if (materialRepository.count() == 0) {
            criarMaterial("Cobre Mel", 35.50, 42.00);
            criarMaterial("Alumínio Grosso", 7.20, 11.50);
            criarMaterial("Ferro Velho", 0.80, 1.50);
            criarMaterial("Latão", 18.00, 24.00);
            System.out.println(">>> Materiais iniciais cadastrados!");
        }
    }

    private void criarMaterial(String nome, double pCompra, double pVenda) {
        Material m = new Material();
        m.setNome(nome);
        m.setPrecoCompra(BigDecimal.valueOf(pCompra));
        m.setPrecoVenda(BigDecimal.valueOf(pVenda));
        m.setQuantidadeEstoque(0.0);
        materialRepository.save(m);
    }
}
