package com.example.demo.service;

import com.example.demo.model.Alimento;
import com.example.demo.repository.AlimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;

// Salvar um novo alimento
    public Alimento salvar(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

// Buscar todos os alimentos
    public List<Alimento> listarTodos() {
        return alimentoRepository.findAll();
    }

// Buscar por categoria
    public List<Alimento> buscarPorCategoria(String categoria) {
        return alimentoRepository.findByCategoriaIgnoreCase(categoria);
    }

// Buscar por nome (contém parte do texto)
    public List<Alimento> buscarPorNome(String nome) {
        return alimentoRepository.findByNomeContainingIgnoreCase(nome);
    }

// Buscar por faixa de preço
    public List<Alimento> buscarPorPrecoEntre(double precoMin, double precoMax) {
        return alimentoRepository.findByPrecoBetween(precoMin, precoMax);
    }

// Buscar por perecibilidade
    public List<Alimento> buscarPorPerecivel(Boolean perecivel) {
        return alimentoRepository.findByPerecivel(perecivel);
    }

    // 🔹 Atualizar alimento existente
    public Alimento atualizar(Long id, Alimento novoAlimento) {
        return alimentoRepository.findById(id)
                .map(alimentoExistente -> {
                    alimentoExistente.setNome(novoAlimento.getNome());
                    alimentoExistente.setCategoria(novoAlimento.getCategoria());
                    alimentoExistente.setPreco(novoAlimento.getPreco());
                    alimentoExistente.setDataFabricacao(novoAlimento.getDataFabricacao());
                    alimentoExistente.setPerecivel(novoAlimento.getPerecivel());
                    return alimentoRepository.save(alimentoExistente);
                })
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado com ID: " + id));
    }

    // 🔹 Deletar alimento por ID
    public void deletar(Long id) {
        if (!alimentoRepository.existsById(id)) {
            throw new RuntimeException("Alimento não encontrado com ID: " + id);
        }
        alimentoRepository.deleteById(id);
    }
}
