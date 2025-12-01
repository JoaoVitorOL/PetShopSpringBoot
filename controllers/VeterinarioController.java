package com.example.demo.controllers;

import com.example.demo.dtos.VeterinarioRequestDTO;
import com.example.demo.dtos.VeterinarioResponseDTO;
import com.example.demo.service.VeterinarioService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    /* ---------------------------------------------------------
       CREATE
    --------------------------------------------------------- */

    @PostMapping
    public ResponseEntity<VeterinarioResponseDTO> criar(
            @Valid @RequestBody VeterinarioRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        VeterinarioResponseDTO criado = veterinarioService.criar(dto);

        URI uri = uriBuilder
                .path("/veterinarios/{id}")
                .buildAndExpand(criado.getId())
                .toUri();

        return ResponseEntity.created(uri).body(criado);
    }

    /* ---------------------------------------------------------
       READ ALL
    --------------------------------------------------------- */


    @GetMapping
    public ResponseEntity<List<VeterinarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(veterinarioService.listarTodos());
    }

    /* ---------------------------------------------------------
       READ ONE
    --------------------------------------------------------- */


    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> buscarPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(veterinarioService.buscarPorId(id));
    }

    /* ---------------------------------------------------------
       UPDATE
    --------------------------------------------------------- */


    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody VeterinarioRequestDTO dto) {

        return ResponseEntity.ok(veterinarioService.atualizar(id, dto));
    }

    /* ---------------------------------------------------------
       DELETE
    --------------------------------------------------------- */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        veterinarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
