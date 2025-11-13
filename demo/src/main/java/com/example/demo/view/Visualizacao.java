package com.example.demo.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Visualizacao {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private UsuarioView usuarioView;

    @Autowired
    private AlimentoView alimentoView;

    @Autowired
    private CarrinhoView carrinhoView;

    public void inicio() {
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> criarMenu();
                case 2 -> verMenu();
                case 3 -> atualizarMenu();
                case 4 -> deletarMenu();
                case 5 -> {
                    System.out.println("\nSaindo do sistema... 👋");
                    return;
                }
                default -> System.out.println("\nOpção inválida! Tente novamente.\n");
            }
        }
    }

    // ======== MENUS PRINCIPAIS ========

    private void exibirMenuPrincipal() {
        System.out.println("\n==============================");
        System.out.println("        MENU PRINCIPAL        ");
        System.out.println("==============================");
        System.out.println("1  Criar");
        System.out.println("2  Ver");
        System.out.println("3  Atualizar");
        System.out.println("4  Deletar");
        System.out.println("5  Sair");
        System.out.println("==============================");
    }

    // ======== SUBMENUS ========

    private void criarMenu() {
        System.out.println("\n--- Criar ---");
        System.out.println("1 - Criar Usuário");
        System.out.println("2 - Criar Alimento");
        System.out.println("3 - Adicionar Alimento ao Carrinho");
        System.out.println("4 - Voltar");
        System.out.println("----------------");

        int opcao = lerOpcao("Escolha: ");

        switch (opcao) {
            case 1 -> executarAcao("criar usuário", usuarioView::criarUsuario);
            case 2 -> executarAcao("criar alimento", alimentoView::criarAlimento);
            case 3 -> executarAcao("adicionar alimento ao carrinho", carrinhoView::adicionarAlimentoCarrinho);
            case 4 -> System.out.println("Voltando ao menu principal...\n");
            default -> System.out.println("Opção inválida!\n");
        }
    }

    private void verMenu() {
        System.out.println("\n--- Ver ---");
        System.out.println("1 - Ver Usuários");
        System.out.println("2 - Ver Alimentos");
        System.out.println("3 - Ver Alimentos por faixa de preço");
        System.out.println("4 - Ver Carrinho de Usuário");
        System.out.println("5 - Voltar");
        System.out.println("----------------");

        int opcao = lerOpcao("Escolha: ");

        switch (opcao) {
            case 1 -> executarAcao("listar usuários", usuarioView::listarUsuarios);
            case 2 -> executarAcao("listar alimentos", alimentoView::listarAlimentos);
            case 3 -> executarAcao("listar alimentos por faixa de preço", alimentoView::listarPorFaixaDePreco);
            case 4 -> executarAcao("listar carrinho", carrinhoView::listarCarrinho);
            case 5 -> System.out.println("Voltando ao menu principal...\n");
            default -> System.out.println("Opção inválida!\n");
        }
    }

    private void atualizarMenu() {
        System.out.println("\n--- Atualizar ---");
        System.out.println("1 - Atualizar Usuário");
        System.out.println("2 - Atualizar Alimento");
        System.out.println("3 - Atualizar Carrinho");
        System.out.println("4 - Voltar");
        System.out.println("----------------");

        int opcao = lerOpcao("Escolha: ");

        switch (opcao) {
            case 1 -> executarAcao("atualizar usuário", usuarioView::atualizarUsuario);
            case 2 -> executarAcao("atualizar alimento", alimentoView::atualizarAlimento);
            case 3 -> executarAcao("atualizar carrinho", carrinhoView::atualizarCarrinho);
            case 4 -> System.out.println("Voltando ao menu principal...\n");
            default -> System.out.println("Opção inválida!\n");
        }
    }

    private void deletarMenu() {
        System.out.println("\n--- Deletar ---");
        System.out.println("1 - Deletar Usuário");
        System.out.println("2 - Deletar Alimento");
        System.out.println("3 - Remover Item do Carrinho");
        System.out.println("4 - Voltar");
        System.out.println("----------------");

        int opcao = lerOpcao("Escolha: ");

        switch (opcao) {
            case 1 -> executarAcao("deletar usuário", usuarioView::deletarUsuario);
            case 2 -> executarAcao("deletar alimento", alimentoView::deletarAlimento);
            case 3 -> executarAcao("deletar item do carrinho", carrinhoView::deletarCarrinho);
            case 4 -> System.out.println("Voltando ao menu principal...\n");
            default -> System.out.println("Opção inválida!\n");
        }
    }

    // ======== MÉTODOS AUXILIARES ========

    private int lerOpcao(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int opcao = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                return opcao;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    private void executarAcao(String descricao, Runnable acao) {
        try {
            acao.run();
            // As próprias views já mostram mensagens, então aqui só confirmamos
            System.out.println("(✔) Ação '" + descricao + "' concluída.\n");
        } catch (Exception e) {
            System.err.println("❌ Erro ao executar " + descricao + ": " + e.getMessage());
        }
    }
}
