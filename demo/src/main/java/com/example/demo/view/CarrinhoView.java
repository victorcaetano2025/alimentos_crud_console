package com.example.demo.view;

import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CarrinhoView {

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Scanner scanner = new Scanner(System.in);

    public void adicionarAlimentoCarrinho() {
        try {
            System.out.println("\n=== Adicionar Alimento ao Carrinho ===");

            System.out.print("ID do Usuário: ");
            Long userId = scanner.nextLong();

            System.out.print("ID do Alimento: ");
            Long alimentoId = scanner.nextLong();
            scanner.nextLine(); // limpar buffer

            carrinhoService.salvar(userId, alimentoId);

            System.out.println("✅ Alimento adicionado ao carrinho!\n");
        } catch (Exception e) {
            System.out.println("❌ Não foi possível adicionar o alimento ao carrinho: " + e.getMessage() + "\n");
            scanner.nextLine(); // limpa buffer em caso de erro
        }
    }

    public void listarCarrinho() {
        System.out.println("\n=== Alimentos no Carrinho ===");
        System.out.print("ID do Usuário: ");

        try {
            Long userId = scanner.nextLong();
            scanner.nextLine(); // limpar buffer

            var usuarioOpt = usuarioRepository.findById(userId);
            if (usuarioOpt.isEmpty()) {
                System.out.println("❌ Usuário não encontrado.\n");
                return;
            }

            var alimentos = carrinhoService.meusAlimentos(userId);
            System.out.println("Carrinho de: " + usuarioOpt.get().getNome());

            if (alimentos.isEmpty()) {
                System.out.println("⚠️ Nenhum alimento no carrinho.");
            } else {
                alimentos.forEach(a -> System.out.println(
                        "ID: " + a.getId() +
                        " | Nome: " + a.getNome() +
                        " | Categoria: " + a.getCategoria() +
                        " | Preço: " + a.getPreco() +
                        " | Perecível: " + a.getPerecivel() +
                        " | Data Fabricação: " + a.getDataFabricacao()
                ));
            }
        } catch (Exception e) {
            System.out.println("❌ Ocorreu um erro: " + e.getMessage());
            scanner.nextLine(); // limpar buffer em caso de erro
        } finally {
            System.out.println(); // separação visual
        }
    }
}
