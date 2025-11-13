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
    
    // 🔹 Atualizar usuário existente
  public Usuario atualizar(Long id, Usuario novoUsuario) {
    if (novoUsuario == null) {
        throw new RuntimeException("Objeto novoUsuario é nulo");
    }

    return usuarioRepository.findById(id)
            .map(usuarioExistente -> {
                if (novoUsuario.getNome() == null || novoUsuario.getNome().isBlank()) {
                    throw new RuntimeException("Nome inválido");
                }
                if (novoUsuario.getIdade() <= 0) {
                    throw new RuntimeException("Idade inválida");
                }

                usuarioExistente.setNome(novoUsuario.getNome());
                usuarioExistente.setIdade(novoUsuario.getIdade());
                return usuarioRepository.save(usuarioExistente);
            })
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
}


    // 🔹 Deletar usuário por ID
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    

}
