package com.example.demo.services;

import com.example.demo.dtos.VacinaRequestDTO;
import com.example.demo.dtos.VacinaResponseDTO;
import com.example.demo.entities.Vacina;
import com.example.demo.repositories.VacinaRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VacinaService {

    private final VacinaRepository repository;

    public VacinaService(VacinaRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public VacinaResponseDTO criar(VacinaRequestDTO dto) {
        Vacina vacina = new Vacina();
        vacina.setNome(dto.getNome());
        vacina.setDescricao(dto.getDescricao());
        vacina.setDuracao(dto.getDuracao());

        Vacina salva = repository.save(vacina);
        return toResponse(salva);
    }


    @Transactional(readOnly = true)
    public List<VacinaResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VacinaResponseDTO buscarPorId(Long id) {
        Vacina vacina = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vacina não encontrada: id=" + id));

        return toResponse(vacina);
    }


    @Transactional
    public VacinaResponseDTO atualizar(Long id, VacinaRequestDTO dto) {
        Vacina vacina = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vacina não encontrada: id=" + id));

        vacina.setNome(dto.getNome());
        vacina.setDescricao(dto.getDescricao());
        vacina.setDuracao(dto.getDuracao());

        Vacina atualizada = repository.save(vacina);
        return toResponse(atualizada);
    }


    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Vacina não encontrada: id=" + id);
        }
        repository.deleteById(id);
    }


    private VacinaResponseDTO toResponse(Vacina vacina) {
        return new VacinaResponseDTO(
                vacina.getIdVacina(),
                vacina.getNome(),
                vacina.getDescricao(),
                vacina.getDuracao()
        );
    }
}
