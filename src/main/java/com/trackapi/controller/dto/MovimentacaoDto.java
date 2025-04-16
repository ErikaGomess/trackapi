package com.trackapi.controller.dto;


import com.trackapi.domain.model.Movimentacao;

import java.time.LocalDateTime;


public record MovimentacaoDto(
        Long id,
        LocalDateTime dataHora,
        SetorDto deSetor,
        SetorDto paraSetor,
        String observacao,
        Long encomendaId
) {

    public MovimentacaoDto(Movimentacao model) {
        this(
                model.getId(),
                model.getDataHora(),
                model.getDeSetor() != null ? new SetorDto(model.getDeSetor()) : null,
                model.getParaSetor() != null ? new SetorDto(model.getParaSetor()) : null,
                model.getObservacao(),
                model.getEncomenda() != null ? model.getEncomenda().getId() : null
        );
    }

    public Movimentacao toModel() {
        Movimentacao model = new Movimentacao();
        model.setId(this.id);
        model.setDataHora(this.dataHora);
        model.setDeSetor(this.deSetor != null ? this.deSetor.toModel() : null);
        model.setParaSetor(this.paraSetor != null ? this.paraSetor.toModel() : null);
        model.setObservacao(this.observacao);

        // Aqui, como só temos o ID, normalmente isso seria setado em outro ponto com o repositório.
        return model;
    }
}


