package com.example.demo.service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

     private final UsuarioRepository usuarioRepository;
// Salvar um novo usuario
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
// Buscar todos os usuarios
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    

}
