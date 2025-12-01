package com.example.demo.controllers;

import com.example.demo.dtos.DonoRequestDTO;
import com.example.demo.dtos.DonoResponseDTO;
import com.example.demo.service.DonoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/donos")
public class DonoController {

    private final DonoService donoService;


    public DonoController(DonoService donoService) {
        this.donoService = donoService;
    }


    @GetMapping
    public ResponseEntity<List<DonoResponseDTO>> listarTodos() {
        List<DonoResponseDTO> lista = donoService.listarTodos();
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DonoResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        DonoResponseDTO dto = donoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<DonoResponseDTO> criar(
            @Valid @RequestBody DonoRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        DonoResponseDTO criado = donoService.criar(dto);

        // Monta a URI do novo recurso: /donos/{id}
        URI uri = uriBuilder.path("/donos/{id}").buildAndExpand(criado.getId()).toUri();

        return ResponseEntity.created(uri).body(criado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DonoResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody DonoRequestDTO dto) {

        DonoResponseDTO atualizado = donoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        donoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
