package com.trackapi.controller;


import com.trackapi.controller.dto.UsuarioDto;
import com.trackapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários Controller", description = "API REST para gerenciamento de usuarios")
public record UsuarioController (UsuarioService usuarioService) {

    @GetMapping
    @Operation(summary = "Buscar todos os usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })
    public ResponseEntity<List<UsuarioDto>> findAll() {
        var usuarios = usuarioService.findAll();
        var usuarioDto = usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(usuarioDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) {
        var usuario = usuarioService.findById(id);
        return ResponseEntity.ok(new UsuarioDto(usuario));
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "usuário criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto) {
        var usuario = usuarioService.create(usuarioDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(location).body(new UsuarioDto(usuario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @RequestBody UsuarioDto usuarioDto) {
        var usuario = usuarioService.update(id, usuarioDto.toModel());
        return ResponseEntity.ok(new UsuarioDto(usuario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
