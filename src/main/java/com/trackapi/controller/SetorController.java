package com.trackapi.controller;


import com.trackapi.controller.dto.SetorDto;
import com.trackapi.service.SetorService;
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
@RequestMapping("/setores")
@Tag(name = "Setores Controller", description = "API REST para gerenciamento de setores")
public record SetorController(SetorService setorService) {

    @GetMapping
    @Operation(summary = "Buscar todos os setores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
    })
    public ResponseEntity<List<SetorDto>> findAll() {
        var setores = setorService.findAll();
        var setoresDto = setores.stream().map(SetorDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(setoresDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar setor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<SetorDto> findById(@PathVariable Long id) {
        var setor = setorService.findById(id);
        return ResponseEntity.ok(new SetorDto(setor));
    }

    @PostMapping
    @Operation(summary = "Criar novo setor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Setor criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos")
    })
    public ResponseEntity<SetorDto> create(@RequestBody SetorDto setorDto) {
        var setor = setorService.create(setorDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(setor.getId())
                .toUri();
        return ResponseEntity.created(location).body(new SetorDto(setor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar setor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Setor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<SetorDto> update(@PathVariable Long id, @RequestBody SetorDto setorDto) {
        var setor = setorService.update(id, setorDto.toModel());
        return ResponseEntity.ok(new SetorDto(setor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar setor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Setor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        setorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
