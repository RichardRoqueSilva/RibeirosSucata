package com.ribeiros.sucatas.repository;

import com.ribeiros.sucatas.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // Busca gastos entre duas datas
    List<Gasto> findByDataBetween(LocalDate inicio, LocalDate fim);
}
