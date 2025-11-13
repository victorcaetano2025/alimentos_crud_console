package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carrinho") // use minúsculo por convenção
@Data
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // <-- troque para 'id' (minúsculo)

    @ManyToOne
    @JoinColumn(name = "id_alimento", nullable = false)
    private Alimento alimento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
