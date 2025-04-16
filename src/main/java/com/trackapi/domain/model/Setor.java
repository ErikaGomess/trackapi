package com.trackapi.domain.model;

import jakarta.persistence.*;

@Entity
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String responsavel;

    public Setor() {}

    public Setor(Long id, String nome, String responsavel) {
        this.id = id;
        this.nome = nome;
        this.responsavel = responsavel;
    }

    // Getters, setters e toString

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}
