package com.trackapi.controller.dto;


import com.trackapi.domain.model.Setor;

public record SetorDto(Long id, String nome, String responsavel) {

    public SetorDto(Setor model) {
        this(model.getId(), model.getNome(), model.getResponsavel());
    }

    public Setor toModel() {
        Setor model = new Setor();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setResponsavel(this.responsavel);
        return model;
    }
}
