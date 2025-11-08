package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
// encontra todos que tiver parte do parametro
    List<Usuario> findBynomeContainingIgnoreCase(String nome);
// encontra os que tiverem a idade no parametro 
    List<Usuario> findByidade(int idade);
}
