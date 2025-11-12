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
        alimento.setPreco(scanner.nextDouble());

        System.out.print("Perecível (true/false): ");
        alimento.setPerecivel(scanner.nextBoolean());
        scanner.nextLine();

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
            alimentos.forEach(a -> System.out.println(
                    "ID: " + a.getId() +
                    " | Nome: " + a.getNome() +
                    " | Categoria: " + a.getCategoria() +
                    " | Preço: " + a.getPreco() +
                    " | Perecível: " + a.getPerecivel() +
                    " | Data: " + a.getDataFabricacao()
            ));
        }
        System.out.println();
    }

    // 🔹 Atualizar alimento (sem buscarPorId)
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
        novo.setPreco(scanner.nextDouble());

        System.out.print("Perecível (true/false): ");
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

    // 🔹 Deletar alimento (sem buscarPorId)
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
}
