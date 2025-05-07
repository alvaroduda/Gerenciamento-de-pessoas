package com.atividade.backend.Model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;


@Entity
@Table (name = "Pessoa")
public class ModelPessoa implements Serializable {

    @Serial
    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private String cidade;
    @Getter @Setter
    private int idade;
    @Getter @Setter
    private String cpf;
    @Getter @Setter
    private double altura;
    @Getter @Setter
    private String whatsapp;


}
