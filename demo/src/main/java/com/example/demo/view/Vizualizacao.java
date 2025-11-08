package com.example.demo.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Vizualizacao {

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private UsuarioView usuarioView;

    @Autowired
    private AlimentoView alimentoView;

    @Autowired
    private CarrinhoView carrinhoView;

    public void inicio() {
        while (true) { // Loop infinito do menu principal
            System.out.println("=== Menu Inicial ===");
            System.out.println("1 - Criar");
            System.out.println("2 - Ver");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    criarMenu();
                    break;
                case 2:
                    verMenu();
                    break;
                case 3:
                    System.out.println("Funcionalidade de atualizar ainda não implementada.\n");
                    break;
                case 4:
                    System.out.println("Funcionalidade de deletar ainda não implementada.\n");
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.\n");
            }
        }
    }

    private void criarMenu() {
        System.out.println("\n=== Criar ===");
        System.out.println("1 - Criar Usuário");
        System.out.println("2 - Criar Alimento");
        System.out.println("3 - Adicionar Alimento ao Carrinho");
        System.out.println("4 - Voltar");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                usuarioView.criarUsuario();
                break;
            case 2:
                alimentoView.criarAlimento();
                break;
            case 3:
                carrinhoView.adicionarAlimentoCarrinho();
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida.\n");
        }
    }

    private void verMenu() {
        System.out.println("\n=== Ver ===");
        System.out.println("1 - Ver Usuários");
        System.out.println("2 - Ver Alimentos");
        System.out.println("3 - Ver Carrinho de Usuário");
        System.out.println("4 - Voltar");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                usuarioView.listarUsuarios();
                break;
            case 2:
                alimentoView.listarAlimentos();
                break;
            case 3:
                carrinhoView.listarCarrinho();
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida.\n");
        }
    }
}
