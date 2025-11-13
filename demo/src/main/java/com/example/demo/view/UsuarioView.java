package com.example.demo.view;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UsuarioView {

    @Autowired
    private UsuarioService usuarioService;

    private final Scanner scanner = new Scanner(System.in);

    // 🔹 Criar usuário
    public void criarUsuario() {
        Usuario usuario = new Usuario();

        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        usuario.setIdade(scanner.nextInt());
        scanner.nextLine();

        usuarioService.salvar(usuario);
        System.out.println("Usuário cadastrado com sucesso!\n");
    }

    // 🔹 Listar todos
    public void listarUsuarios() {
        System.out.println("\n=== Lista de Usuários ===");
        List<Usuario> usuarios = usuarioService.listarTodos();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.\n");
        } else {
            usuarios.forEach(u -> System.out.println(
                    "ID: " + u.getId() + " | Nome: " + u.getNome() + " | Idade: " + u.getIdade()
            ));
        }
        System.out.println();
    }

    // 🔹 Atualizar usuário
    public void atualizarUsuario() {
        System.out.println("\n=== Atualizar Usuário ===");
        System.out.print("Digite o ID do usuário a atualizar: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Usuario novo = new Usuario();
        System.out.print("Novo nome: ");
        novo.setNome(scanner.nextLine());

        System.out.print("Nova idade: ");
        novo.setIdade(scanner.nextInt());
        scanner.nextLine();

        try {
            usuarioService.atualizar(id, novo);
            System.out.println("Usuário atualizado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // 🔹 Deletar usuário
    public void deletarUsuario() {
        System.out.println("\n=== Deletar Usuário ===");
        System.out.print("Digite o ID do usuário: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            usuarioService.deletar(id);
            System.out.println("Usuário deletado com sucesso!\n");
        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
