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

    // 🔹 Adicionar alimento ao carrinho
    public void adicionarAlimentoCarrinho() {
        try {
            System.out.println("\n=== Adicionar Alimento ao Carrinho ===");
            System.out.print("ID do Usuário: ");
            Long userId = scanner.nextLong();

            System.out.print("ID do Alimento: ");
            Long alimentoId = scanner.nextLong();
            scanner.nextLine();

            carrinhoService.salvar(userId, alimentoId);
            System.out.println("✅ Alimento adicionado ao carrinho!\n");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // 🔹 Listar alimentos do carrinho de um usuário
    public void listarCarrinho() {
        System.out.println("\n=== Alimentos no Carrinho ===");
        System.out.print("ID do Usuário: ");

        try {
            Long userId = scanner.nextLong();
            scanner.nextLine();

            var usuarioOpt = usuarioRepository.findById(userId);
            if (usuarioOpt.isEmpty()) {
                System.out.println("❌ Usuário não encontrado.\n");
                return;
            }

            var alimentos = carrinhoService.meusAlimentos(userId);
            System.out.println("Carrinho de: " + usuarioOpt.get().getNome());

            if (alimentos.isEmpty()) {
                System.out.println("⚠️ Nenhum alimento no carrinho.\n");
            } else {
                alimentos.forEach(a -> System.out.println(
                        "ID: " + a.getId() +
                        " | Nome: " + a.getNome() +
                        " | Categoria: " + a.getCategoria() +
                        " | Preço: " + a.getPreco()
                ));
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // 🔹 Atualizar item do carrinho
    public void atualizarCarrinho() {
        System.out.println("\n=== Atualizar Carrinho ===");
        System.out.print("Digite o ID do Carrinho: ");
        Long carrinhoId = scanner.nextLong();

        System.out.print("Novo ID de Usuário (0 = manter): ");
        Long novoUserId = scanner.nextLong();

        System.out.print("Novo ID de Alimento (0 = manter): ");
        Long novoAlimentoId = scanner.nextLong();
        scanner.nextLine();

        try {
            carrinhoService.atualizar(
                carrinhoId,
                novoUserId == 0 ? null : novoUserId,
                novoAlimentoId == 0 ? null : novoAlimentoId
            );
            System.out.println("✅ Carrinho atualizado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar carrinho: " + e.getMessage());
        }
    }

    // 🔹 Deletar item do carrinho
    public void deletarCarrinho() {
        System.out.println("\n=== Remover Item do Carrinho ===");
        System.out.print("ID do Carrinho: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            carrinhoService.deletar(id);
            System.out.println("✅ Item removido do carrinho!\n");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
}
