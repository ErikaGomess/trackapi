package com.trackapi.controller;

import com.trackapi.controller.dto.EncomendaDto;
import com.trackapi.service.EncomendaService;
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
@RequestMapping("/encomendas")
@Tag(name = "Encomendas Controller", description = "API REST para gerenciamento de encomendas")
public record EncomendaController(EncomendaService encomendaService) {

    @GetMapping
    @Operation(summary = "Buscar todas as encomendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })
    public ResponseEntity<List<EncomendaDto>> findAll() {
        var encomendas = encomendaService.findAll();
        var encomendasDto = encomendas.stream().map(EncomendaDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(encomendasDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar encomenda por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Encomenda não encontrada")
    })
    public ResponseEntity<EncomendaDto> findById(@PathVariable Long id) {
        var encomenda = encomendaService.findById(id);
        return ResponseEntity.ok(new EncomendaDto(encomenda));
    }

    @PostMapping
    @Operation(summary = "Criar nova encomenda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Encomenda criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<EncomendaDto> create(@RequestBody EncomendaDto encomendaDto) {
        var encomenda = encomendaService.create(encomendaDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(encomenda.getId())
                .toUri();
        return ResponseEntity.created(location).body(new EncomendaDto(encomenda));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar encomenda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encomenda atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Encomenda não encontrada"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<EncomendaDto> update(@PathVariable Long id, @RequestBody EncomendaDto encomendaDto) {
        var encomenda = encomendaService.update(id, encomendaDto.toModel());
        return ResponseEntity.ok(new EncomendaDto(encomenda));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar encomenda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Encomenda deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Encomenda não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        encomendaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
