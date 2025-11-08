package com.example.demo;

import com.example.demo.view.Vizualizacao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
	
    // 🔹 Bean para iniciar o menu de console após o Spring Boot iniciar
    @Bean
    public CommandLineRunner run(Vizualizacao vizualizacao) {
        return args -> vizualizacao.inicio();
    }
}
