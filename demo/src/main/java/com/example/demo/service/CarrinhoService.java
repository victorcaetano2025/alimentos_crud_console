package com.example.demo.service;

import com.example.demo.model.Alimento;
import com.example.demo.model.Carrinho;
import com.example.demo.model.Usuario;
import com.example.demo.repository.AlimentoRepository;
import com.example.demo.repository.CarrinhoRepository;
import com.example.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final UsuarioRepository usuarioRepository;
    private final AlimentoRepository alimentoRepository;
    private final CarrinhoRepository carrinhoRepository;

    // Adicionar um alimento ao carrinho do usuário
    public void salvar(Long userId, Long alimentoId) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Alimento alimento = alimentoRepository.findById(alimentoId)
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado"));

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        carrinho.setAlimento(alimento);

        // Salva no repository injetado
        carrinhoRepository.save(carrinho);
    }

    // Listar todos os alimentos do carrinho de um usuário
    public List<Alimento> meusAlimentos(Long usuarioId) {
        // ⚠️ Esse método precisa existir no CarrinhoRepository
        return carrinhoRepository.findAlimentosByUsuarioId(usuarioId);
    }
}
