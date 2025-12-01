package com.example.demo.controllers;

import com.example.demo.dtos.VacinaRequestDTO;
import com.example.demo.dtos.VacinaResponseDTO;
import com.example.demo.services.VacinaService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    private final VacinaService vacinaService;


    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    /* ---------------------------------------------------------
       CREATE
    --------------------------------------------------------- */


    @PostMapping
    public ResponseEntity<VacinaResponseDTO> criar(
            @Valid @RequestBody VacinaRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        VacinaResponseDTO criada = vacinaService.criar(dto);

        URI uri = uriBuilder
                .path("/vacinas/{id}")
                .buildAndExpand(criada.getIdVacina())
                .toUri();

        return ResponseEntity.created(uri).body(criada);
    }

    /* ---------------------------------------------------------
       READ ALL
    --------------------------------------------------------- */


    @GetMapping
    public ResponseEntity<List<VacinaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(vacinaService.listarTodas());
    }

    /* ---------------------------------------------------------
       READ ONE
    --------------------------------------------------------- */


    @GetMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vacinaService.buscarPorId(id));
    }

    /* ---------------------------------------------------------
       UPDATE
    --------------------------------------------------------- */


    @PutMapping("/{id}")
    public ResponseEntity<VacinaResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody VacinaRequestDTO dto) {

        return ResponseEntity.ok(vacinaService.atualizar(id, dto));
    }

    /* ---------------------------------------------------------
       DELETE
    --------------------------------------------------------- */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        vacinaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
