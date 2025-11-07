package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "alimento")
@Data

public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    
    
    private String alimento;          
    private int dataFabricacao;    
    private String classe;        
    private double preco;
    private Boolean perecivel;
    
    
    
}