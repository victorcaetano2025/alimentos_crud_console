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
                case 3 -> System.out.println("\nFuncionalidade de atualizar ainda não implementada.\n");
                case 4 -> System.out.println("\nFuncionalidade de deletar ainda não implementada.\n");
                case 5 -> {
                    System.out.println("\nSaindo do sistema... ");
                    return;
                }
                default -> System.out.println("\nOpção inválida! Tente novamente.\n");
            }
        }
    }

    // ======== MENUS ========

    private void exibirMenuPrincipal() {
        System.out.println("\n==============================");
        System.out.println("        MENU PRINCIPAL        ");
        System.out.println("==============================");
        System.out.println("1  Criar");
        System.out.println("2  Ver");
        System.out.println("3  Atualizar");
        System.out.println("4  Deletar");
        System.out.println("5 Sair");
        System.out.println("==============================");
    }

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
            case 4 -> {
                System.out.println("Voltando ao menu principal...\n");
                return;
            }
            default -> System.out.println("Opção inválida!\n");
        }
    }

    private void verMenu() {
        System.out.println("\n--- Ver ---");
        System.out.println("1 - Ver Usuários");
        System.out.println("2 - Ver Alimentos");
        System.out.println("3 - Ver Carrinho de Usuário");
        System.out.println("4 - Voltar");
        System.out.println("----------------");

        int opcao = lerOpcao("Escolha: ");

        switch (opcao) {
            case 1 -> executarAcao("listar usuários", usuarioView::listarUsuarios);
            case 2 -> executarAcao("listar alimentos", alimentoView::listarAlimentos);
            case 3 -> executarAcao("listar carrinho", carrinhoView::listarCarrinho);
            case 4 -> {
                System.out.println("Voltando ao menu principal...\n");
                return;
            }
            default -> System.out.println("Opção inválida!\n");
        }
    }

    // ======== MÉTODOS AUXILIARES ========

    /**
     * Lê uma opção numérica com tratamento de erro.
     */
    private int lerOpcao(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int opcao = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
                return opcao;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine(); // limpa a entrada errada
            }
        }
    }

    /**
     * Executa uma ação com tratamento de exceção.
     */
    private void executarAcao(String descricao, Runnable acao) {
        try {
            acao.run();
            System.out.println("Ação '" + descricao + "' concluída com sucesso!\n");
        } catch (Exception e) {
            System.err.println("Erro ao executar " + descricao + ": " + e.getMessage());
        }
    }
}
