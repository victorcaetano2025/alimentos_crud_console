package com.example.demo.repository;

import com.example.demo.model.Alimento;
import com.example.demo.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    // 🔹 Lista todos os alimentos de um usuário
    @Query("SELECT c.alimento FROM Carrinho c WHERE c.usuario.id = :usuarioId")
    List<Alimento> findAlimentosByUsuarioId(@Param("usuarioId") Long usuarioId);

    // 🔹 Lista todos os carrinhos que contêm determinado alimento
    List<Carrinho> findByAlimentoId(Long alimentoId);
}
