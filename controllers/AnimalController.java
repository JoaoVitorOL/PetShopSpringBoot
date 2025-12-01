package com.example.demo.controllers;

import com.example.demo.dtos.AnimalRequestDTO;
import com.example.demo.dtos.AnimalResponseDTO;
import com.example.demo.services.AnimalService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    /* ---------------------------------------------------------
       CREATE
    --------------------------------------------------------- */


    @PostMapping
    public ResponseEntity<AnimalResponseDTO> create(
            @Valid @RequestBody AnimalRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        AnimalResponseDTO response = animalService.create(dto);

        URI uri = uriBuilder
                .path("/animais/{id}")
                .buildAndExpand(response.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    /* ---------------------------------------------------------
       READ ALL
    --------------------------------------------------------- */


    @GetMapping
    public ResponseEntity<List<AnimalResponseDTO>> findAll() {
        return ResponseEntity.ok(animalService.findAll());
    }

    /* ---------------------------------------------------------
       READ ONE
    --------------------------------------------------------- */

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(animalService.findById(id));
    }

    /* ---------------------------------------------------------
       UPDATE
    --------------------------------------------------------- */


    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDTO> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AnimalRequestDTO dto) {

        return ResponseEntity.ok(animalService.update(id, dto));
    }

    /* ---------------------------------------------------------
       DELETE
    --------------------------------------------------------- */


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        animalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
