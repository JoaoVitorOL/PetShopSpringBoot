package com.example.demo.services;

import com.example.demo.entities.Animal;
import com.example.demo.entities.Dono;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.repositories.DonoRepository;
import com.example.demo.dtos.AnimalRequestDTO;
import com.example.demo.dtos.AnimalResponseDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final DonoRepository donoRepository;

    public AnimalService(AnimalRepository animalRepository, DonoRepository donoRepository) {
        this.animalRepository = animalRepository;
        this.donoRepository = donoRepository;
    }

    /* ---------------------------------------------------------
       CREATE
    --------------------------------------------------------- */
    @Transactional
    public AnimalResponseDTO create(AnimalRequestDTO dto) {

        Dono dono = donoRepository.findById(dto.getIdDono())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        Animal animal = new Animal();
        animal.setNome(dto.getNome());
        animal.setEspecie(dto.getEspecie());
        animal.setRaca(dto.getRaca());
        animal.setDataNascimento(dto.getDataNascimento());
        animal.setSexo(dto.getSexo());
        animal.setPeso(dto.getPeso());
        animal.setDono(dono);

        Animal saved = animalRepository.save(animal);
        return toResponse(saved);
    }

    /* ---------------------------------------------------------
       READ ALL
    --------------------------------------------------------- */
    public List<AnimalResponseDTO> findAll() {
        return animalRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /* ---------------------------------------------------------
       READ ONE
    --------------------------------------------------------- */
    public AnimalResponseDTO findById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        return toResponse(animal);
    }

    /* ---------------------------------------------------------
       UPDATE
    --------------------------------------------------------- */
    @Transactional
    public AnimalResponseDTO update(Long id, AnimalRequestDTO dto) {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

        Dono dono = donoRepository.findById(dto.getIdDono())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        animal.setNome(dto.getNome());
        animal.setEspecie(dto.getEspecie());
        animal.setRaca(dto.getRaca());
        animal.setDataNascimento(dto.getDataNascimento());
        animal.setSexo(dto.getSexo());
        animal.setPeso(dto.getPeso());
        animal.setDono(dono);

        Animal updated = animalRepository.save(animal);
        return toResponse(updated);
    }

    /* ---------------------------------------------------------
       DELETE
    --------------------------------------------------------- */
    @Transactional
    public void delete(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal não encontrado");
        }
        animalRepository.deleteById(id);
    }

    /* ---------------------------------------------------------
       MAPEADOR (ENTITY → RESPONSE)
    --------------------------------------------------------- */
    private AnimalResponseDTO toResponse(Animal animal) {
        return new AnimalResponseDTO(
                animal.getId(),
                animal.getNome(),
                animal.getEspecie(),
                animal.getRaca(),
                animal.getDataNascimento(),
                animal.getDono().getId(),
                animal.getDono().getNome(),
                animal.getSexo(),
                animal.getPeso()
        );
    }
}