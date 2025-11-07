package com.example.demo.view;

import java.util.Scanner;

//import com.example.demo.service.UsuarioService;

public class Vizualizacao {

  Scanner scanner = new Scanner(System.in);
  public int entrada;

  public Vizualizacao() {
  }
/*
 * ================================================
 * Essa classe e inicializada ja usando scanner 
 * os metodos devem ter while ou for para o scanner
 * e mensagem para receber os devidos parametros
 * ================================================
 */
  public void inicio() {
    System.out.println("menu inicial");
    System.out.println("1 - criar\n2 - ver\n3 - atulizar\n4 - deletar\n");

    int entrada = scanner.nextInt(); // lê a linha digitada
    switch (entrada) {
      case 1:
        criar();
        break;

      default:
        break;
    }
  }

  // usar o service
  public void criar() {
    switch (entrada) {
      case 1: // service criar usuario

        break;
      case 2: // service criar alimento

        break;
      case 3: // associar alimento a um usuario(cujo criar carrinho)

        break;
      case 4: // Voltar
        inicio();
        break;

      default:
        break;
    }
  }

  // usar o service
  public void ver() {
    switch (entrada) {
      case 1: // ver todos usuarios

        break;
      case 2: // ver todos alimento

        break;
      case 3: // ver todos alimento de um usuario
      ver();
        break;
      case 4: // Voltar
        inicio();
        break;

      default:
        break;
    }
  }

  // usar o service
  public void atulizar() {
    switch (entrada) {
      case 1: // atulizar usuario por id

        break;
      case 2: // atulizar alimento por id

        break;
      case 3: // atulizar associacao????

        break;
      case 4: // Voltar
        inicio();
        break;

      default:
        break;
    }
  }

  public void deletar() {
    switch (entrada) {
      case 1: // deletar usuario por id

        break;
      case 2: // deletar alimento por id

        break;
      case 3: // deletar associcao por id(user e food)

        break;
      case 4: // Voltar
        inicio();
        break;

      default:
        break;
    }
  }

}