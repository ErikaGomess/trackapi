package com.trackapi.controller.dto;


import com.trackapi.domain.model.Usuario;

public record UsuarioDto(Long id, String nome, String email, String senha, String role, SetorDto setor) {

    public UsuarioDto(Usuario model) {
        this(model.getId(), model.getNome(), model.getEmail(), model.getSenha(), model.getRole(),
                model.getSetor() != null ? new SetorDto(model.getSetor()) : null);
    }

    public Usuario toModel() {
        Usuario model = new Usuario();
        model.setId(this.id);
        model.setNome(this.nome);
        model.setEmail(this.email);
        model.setSenha(this.senha);
        model.setRole(this.role);
        model.setSetor(this.setor != null ? this.setor.toModel() : null);
        return model;
    }
}

