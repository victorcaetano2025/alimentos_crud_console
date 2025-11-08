package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Carrinho")
@Data
public class Carrinho{
//id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
//alimento
    @ManyToOne
    @JoinColumn(name = "id_alimento", nullable = false)
    private Alimento alimento;
//usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}