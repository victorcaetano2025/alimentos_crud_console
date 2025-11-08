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

    public void criarUsuario() {
        Usuario usuario = new Usuario();

        System.out.println("\n=== Cadastro de Usuário ===");
        System.out.print("Nome: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Idade: ");
        usuario.setIdade(scanner.nextInt());
        scanner.nextLine(); // limpar buffer

        usuarioService.salvar(usuario);
        System.out.println("✅ Usuário cadastrado com sucesso!\n");
    }

    public void listarUsuarios() {
        System.out.println("\n=== Lista de Usuários ===");
        List<Usuario> usuarios = usuarioService.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            usuarios.forEach(u -> System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Idade: " + u.getIdade()));
        }
        System.out.println();
    }
}
