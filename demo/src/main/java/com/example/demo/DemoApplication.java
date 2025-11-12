package com.example.demo;

import com.example.demo.view.Visualizacao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // 🔹 Executa o menu principal no console após iniciar o Spring Boot
    @Bean
    public CommandLineRunner run(Visualizacao visualizacao) {
        return args -> visualizacao.inicio();
    }
}
