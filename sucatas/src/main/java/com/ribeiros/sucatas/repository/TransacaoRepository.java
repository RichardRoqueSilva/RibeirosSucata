package com.ribeiros.sucatas.repository;
import com.ribeiros.sucatas.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    // Busca transações entre duas datas/horas
    List<Transacao> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
