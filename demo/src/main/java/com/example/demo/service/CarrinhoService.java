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
        // Esse método precisa existir no CarrinhoRepository
        return carrinhoRepository.findAlimentosByUsuarioId(usuarioId);
    }
    // 🔹 Atualizar um item do carrinho (trocar alimento ou usuário, se fizer sentido)
    public Carrinho atualizar(Long carrinhoId, Long novoUsuarioId, Long novoAlimentoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado com ID: " + carrinhoId));

        if (novoUsuarioId != null) {
            Usuario novoUsuario = usuarioRepository.findById(novoUsuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + novoUsuarioId));
            carrinho.setUsuario(novoUsuario);
        }

        if (novoAlimentoId != null) {
            Alimento novoAlimento = alimentoRepository.findById(novoAlimentoId)
                    .orElseThrow(() -> new RuntimeException("Alimento não encontrado com ID: " + novoAlimentoId));
            carrinho.setAlimento(novoAlimento);
        }

        return carrinhoRepository.save(carrinho);
    }

    // 🔹 Deletar um item do carrinho
    public void deletar(Long carrinhoId) {
        if (!carrinhoRepository.existsById(carrinhoId)) {
            throw new RuntimeException("Carrinho não encontrado com ID: " + carrinhoId);
        }
        carrinhoRepository.deleteById(carrinhoId);
    }

    // 🔹 Deletar todos os itens do carrinho de um usuário (opcional)
    public void deletarPorUsuario(Long usuarioId) {
        List<Alimento> alimentos = carrinhoRepository.findAlimentosByUsuarioId(usuarioId);
        if (alimentos.isEmpty()) {
            throw new RuntimeException("Nenhum item encontrado no carrinho do usuário ID: " + usuarioId);
        }

        List<Carrinho> carrinhos = carrinhoRepository.findAll()
                .stream()
                .filter(c -> c.getUsuario().getId().equals(usuarioId))
                .toList();

        carrinhoRepository.deleteAll(carrinhos);
    }
}
