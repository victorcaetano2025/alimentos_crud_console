package com.example.demo.repository;

import com.example.demo.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    // 🔹 Buscar alimentos por parte do nome (case insensitive)
    List<Alimento> findByNomeContainingIgnoreCase(String nome);

    // 🔹 Buscar alimentos por categoria
    List<Alimento> findByCategoriaIgnoreCase(String categoria);

    // 🔹 Buscar alimentos por faixa de preço
    List<Alimento> findByPrecoBetween(double precoMin, double precoMax);

    // 🔹 Buscar alimentos perecíveis / não perecíveis
    List<Alimento> findByPerecivel(Boolean perecivel);
}
