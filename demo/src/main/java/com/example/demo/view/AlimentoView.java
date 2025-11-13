package com.example.demo.view;

import com.example.demo.model.Alimento;
import com.example.demo.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class AlimentoView {

    @Autowired
    private AlimentoService alimentoService;

    private final Scanner scanner = new Scanner(System.in);

    // 🔹 Criar alimento
    public void criarAlimento() {
        Alimento alimento = new Alimento();

        System.out.println("\n=== Cadastro de Alimento ===");
        System.out.print("Nome: ");
        alimento.setNome(scanner.nextLine());

        System.out.print("Categoria: ");
        alimento.setCategoria(scanner.nextLine());

        System.out.print("Preço: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido! Digite um número: ");
            scanner.next();
        }
        alimento.setPreco(scanner.nextDouble());

        System.out.print("Perecível (true/false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.print("Valor inválido! Digite true ou false: ");
            scanner.next();
        }
        alimento.setPerecivel(scanner.nextBoolean());
        scanner.nextLine(); // limpa o buffer

        System.out.print("Data de fabricação (AAAA-MM-DD): ");
        alimento.setDataFabricacao(LocalDate.parse(scanner.nextLine()));

        alimentoService.salvar(alimento);
        System.out.println("✅ Alimento cadastrado com sucesso!\n");
    }

    // 🔹 Listar todos
    public void listarAlimentos() {
        System.out.println("\n=== Lista de Alimentos ===");
        List<Alimento> alimentos = alimentoService.listarTodos();

        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado.\n");
        } else {
            alimentos.forEach(this::exibirAlimento);
        }
        System.out.println();
    }

    // 🔹 Listar alimentos por faixa de preço (Min < alimento < Max)
    public void listarPorFaixaDePreco() {
        System.out.println("\n=== Buscar Alimentos por Faixa de Preço ===");
        System.out.print("Preço mínimo: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido! Digite um número: ");
            scanner.next();
        }
        double precoMin = scanner.nextDouble();

        System.out.print("Preço máximo: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido! Digite um número: ");
            scanner.next();
        }
        double precoMax = scanner.nextDouble();
        scanner.nextLine(); // limpar buffer

        List<Alimento> alimentos = alimentoService.buscarPorPrecoEntre(precoMin, precoMax);

        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento encontrado entre R$ " + precoMin + " e R$ " + precoMax + ".\n");
        } else {
            System.out.println("\nAlimentos com preço entre R$ " + precoMin + " e R$ " + precoMax + ":");
            alimentos.forEach(this::exibirAlimento);
        }
        System.out.println();
    }

    // 🔹 Atualizar alimento
    public void atualizarAlimento() {
        System.out.println("\n=== Atualizar Alimento ===");
        System.out.print("Digite o ID do alimento a atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Alimento novo = new Alimento();
        System.out.print("Novo nome: ");
        novo.setNome(scanner.nextLine());

        System.out.print("Nova categoria: ");
        novo.setCategoria(scanner.nextLine());

        System.out.print("Novo preço: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Valor inválido! Digite um número: ");
            scanner.next();
        }
        novo.setPreco(scanner.nextDouble());

        System.out.print("Perecível (true/false): ");
        while (!scanner.hasNextBoolean()) {
            System.out.print("Valor inválido! Digite true ou false: ");
            scanner.next();
        }
        novo.setPerecivel(scanner.nextBoolean());
        scanner.nextLine();

        System.out.print("Nova data de fabricação (AAAA-MM-DD): ");
        novo.setDataFabricacao(LocalDate.parse(scanner.nextLine()));

        try {
            alimentoService.atualizar(id, novo);
            System.out.println("✅ Alimento atualizado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }

    // 🔹 Deletar alimento
    public void deletarAlimento() {
        System.out.println("\n=== Deletar Alimento ===");
        System.out.print("Digite o ID do alimento a deletar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            alimentoService.deletar(id);
            System.out.println("✅ Alimento deletado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("❌ Erro ao deletar: " + e.getMessage());
        }
    }

    // 🔹 Método auxiliar para exibir alimento formatado
    private void exibirAlimento(Alimento a) {
        System.out.printf("ID: %d | Nome: %s | Categoria: %s | Preço: R$ %.2f | Perecível: %s | Data: %s%n",
                a.getId(), a.getNome(), a.getCategoria(), a.getPreco(), a.getPerecivel(), a.getDataFabricacao());
    }
}
