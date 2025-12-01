package com.example.demo.controllers;

import com.example.demo.dtos.ConsultaRequestDTO;
import com.example.demo.dtos.ConsultaResponseDTO;
import com.example.demo.services.ConsultaService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    /* ---------------------------------------------------------
       CREATE
    --------------------------------------------------------- */


    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> criar(
            @Valid @RequestBody ConsultaRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        ConsultaResponseDTO response = consultaService.criar(dto);

        URI uri = uriBuilder
                .path("/consultas/{id}")
                .buildAndExpand(response.getIdConsulta())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    /* ---------------------------------------------------------
       READ ALL
    --------------------------------------------------------- */


    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listar() {
        return ResponseEntity.ok(consultaService.listar());
    }

    /* ---------------------------------------------------------
       READ ONE
    --------------------------------------------------------- */


    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id));
    }

    /* ---------------------------------------------------------
       UPDATE
    --------------------------------------------------------- */


    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody ConsultaRequestDTO dto) {

        return ResponseEntity.ok(consultaService.atualizar(id, dto));
    }

    /* ---------------------------------------------------------
       DELETE
    --------------------------------------------------------- */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        consultaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
