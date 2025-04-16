package com.trackapi.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Setor deSetor;
    private Setor paraSetor;
    private LocalDateTime dataHora;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "encomenda_id")
    private Encomenda encomenda;

    public Movimentacao() {}

    public Movimentacao(Long id, Setor deSetor, Setor paraSetor, LocalDateTime dataHora, String observacao, Encomenda encomenda) {
        this.id = id;
        this.deSetor = deSetor;
        this.paraSetor = paraSetor;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.encomenda = encomenda;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Setor getDeSetor() {
        return deSetor;
    }

    public void setDeSetor(Setor deSetor) {
        this.deSetor = deSetor;
    }

    public Setor getParaSetor() {
        return paraSetor;
    }

    public void setParaSetor(Setor paraSetor) {
        this.paraSetor = paraSetor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Encomenda getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id=" + id +
                ", deSetor='" + deSetor + '\'' +
                ", paraSetor='" + paraSetor + '\'' +
                '}';
    }
}
