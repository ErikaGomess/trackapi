package com.trackapi.domain.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tb_encomenda")
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoRastreamento;
    private String descricao;
    private String localAtual;
    private String status;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;

    @OneToMany(mappedBy = "encomenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movimentacao> movimentacoes = new HashSet<>();

    // Construtores
    public Encomenda() {}

    public Encomenda(Long id, String codigoRastreamento, String descricao, String localAtual, String status,
                     LocalDateTime dataCriacao, LocalDateTime ultimaAtualizacao) {
        this.id = id;
        this.codigoRastreamento = codigoRastreamento;
        this.descricao = descricao;
        this.localAtual = localAtual;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRastreamento() {
        return codigoRastreamento;
    }

    public void setCodigoRastreamento(String codigoRastreamento) {
        this.codigoRastreamento = codigoRastreamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalAtual() {
        return localAtual;
    }

    public void setLocalAtual(String localAtual) {
        this.localAtual = localAtual;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Set<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(Set<Movimentacao> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }

    // toString
    @Override
    public String toString() {
        return "Encomenda{" +
                "id=" + id +
                ", codigoRastreamento='" + codigoRastreamento + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
