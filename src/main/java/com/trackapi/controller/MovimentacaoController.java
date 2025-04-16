package com.trackapi.controller;

import com.trackapi.controller.dto.MovimentacaoDto;
import com.trackapi.service.MovimentacaoService;
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
@RequestMapping("/movimentacoes")
@Tag(name = "Movimentações Controller", description = "API REST para gerenciamento de movimentações de encomendas entre setores")
public record MovimentacaoController(MovimentacaoService movimentacaoService) {

    @GetMapping
    @Operation(summary = "Buscar todas as movimentações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })
    public ResponseEntity<List<MovimentacaoDto>> findAll() {
        var movimentacoes = movimentacaoService.findAll();
        var movimentacoesDto = movimentacoes.stream().map(MovimentacaoDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(movimentacoesDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar movimentação por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Movimentação não encontrada")
    })
    public ResponseEntity<MovimentacaoDto> findById(@PathVariable Long id) {
        var movimentacao = movimentacaoService.findById(id);
        return ResponseEntity.ok(new MovimentacaoDto(movimentacao));
    }

    @PostMapping
    @Operation(summary = "Criar nova movimentação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movimentação criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<MovimentacaoDto> create(@RequestBody MovimentacaoDto movimentacaoDto) {
        var movimentacao = movimentacaoService.create(movimentacaoDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movimentacao.getId())
                .toUri();
        return ResponseEntity.created(location).body(new MovimentacaoDto(movimentacao));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar movimentação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimentação atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Movimentação não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<MovimentacaoDto> update(@PathVariable Long id, @RequestBody MovimentacaoDto movimentacaoDto) {
        var movimentacao = movimentacaoService.update(id, movimentacaoDto.toModel());
        return ResponseEntity.ok(new MovimentacaoDto(movimentacao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar movimentação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movimentação deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Movimentação não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimentacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
