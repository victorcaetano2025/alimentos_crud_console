package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "alimento")
@Data
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String nome; // nome do alimento
    private LocalDate dataFabricacao; // tipo de data mais adequado
    private String categoria; // tipo ou categoria do alimento
    private double preco; // valor ex: R$ 22,14
    private Boolean perecivel; // true ou false
//associação no carrinho
    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Carrinho> carrinhos;

}
