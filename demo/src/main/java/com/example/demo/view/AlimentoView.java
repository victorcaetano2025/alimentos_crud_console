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

    public void criarAlimento() {
        Alimento alimento = new Alimento();

        System.out.println("\n=== Cadastro de Alimento ===");
        System.out.print("Nome do alimento: ");
        alimento.setNome(scanner.nextLine());

        System.out.print("Categoria: ");
        alimento.setCategoria(scanner.nextLine());

        System.out.print("Preço: ");
        alimento.setPreco(scanner.nextDouble());

        System.out.print("O alimento é perecível? (true/false): ");
        alimento.setPerecivel(scanner.nextBoolean());
        scanner.nextLine(); // limpar buffer

        System.out.print("Data de fabricação (AAAA-MM-DD): ");
        alimento.setDataFabricacao(LocalDate.parse(scanner.nextLine()));

        alimentoService.salvar(alimento);
        System.out.println("✅ Alimento salvo com sucesso!\n");
    }

    public void listarAlimentos() {
        System.out.println("\n=== Lista de Alimentos ===");
        List<Alimento> alimentos = alimentoService.listarTodos();
        if (alimentos.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado.");
        } else {
            alimentos.forEach(a -> System.out.println("ID: " + a.getId() + " | Nome: " + a.getNome() +
                    " | Categoria: " + a.getCategoria() + " | Preço: " + a.getPreco() +
                    " | Perecível: " + a.getPerecivel() + " | Data Fabricação: " + a.getDataFabricacao()));
        }
        System.out.println();
    }
}
