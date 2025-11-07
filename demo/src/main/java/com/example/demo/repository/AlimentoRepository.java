package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Alimento;
import java.util.List;


public interface AlimentoRepository extends JpaRepository<Alimento, Long>{
    List<Alimento> findByalimentoContainingIgnoreCase(String alimento);
    List<Alimento> findBydataFabricacao(int dataFabricacao);
    List<Alimento> findByclasse(String classe);
    List<Alimento> findBypreco(double preco);
    List<Alimento> findByperecivel(Boolean perecivel);

    

}