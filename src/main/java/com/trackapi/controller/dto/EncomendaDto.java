package com.trackapi.controller.dto;


import com.trackapi.domain.model.Encomenda;
import com.trackapi.domain.model.Movimentacao;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record EncomendaDto(
        Long id,
        String codigoRastreamento,
        String descricao,
        String localAtual,
        String status,
        LocalDateTime dataCriacao,
        LocalDateTime ultimaAtualizacao,
        Set<MovimentacaoDto> movimentacoes
) {

    public EncomendaDto(Encomenda model) {
        this(
                model.getId(),
                model.getCodigoRastreamento(),
                model.getDescricao(),
                model.getLocalAtual(),
                model.getStatus(),
                model.getDataCriacao(),
                model.getUltimaAtualizacao(),
                model.getMovimentacoes() != null
                        ? model.getMovimentacoes().stream().map(MovimentacaoDto::new).collect(Collectors.toSet())
                        : null
        );
    }

    public Encomenda toModel() {
        Encomenda model = new Encomenda();
        model.setId(this.id);
        model.setCodigoRastreamento(this.codigoRastreamento);
        model.setDescricao(this.descricao);
        model.setLocalAtual(this.localAtual);
        model.setStatus(this.status);
        model.setDataCriacao(this.dataCriacao);
        model.setUltimaAtualizacao(this.ultimaAtualizacao);
        model.setMovimentacoes(
                this.movimentacoes != null
                        ? this.movimentacoes.stream().map(MovimentacaoDto::toModel).collect(Collectors.toSet())
                        : null
        );
        return model;
    }
}
