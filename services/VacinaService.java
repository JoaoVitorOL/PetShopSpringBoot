package com.example.demo.services;

import com.example.demo.dtos.VacinaRequestDTO;
import com.example.demo.dtos.VacinaResponseDTO;
import com.example.demo.entities.Vacina;
import com.example.demo.repositories.VacinaRepository;
import com.example.demo.exceptions.BusinessRuleException;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacinaService {

    private final VacinaRepository vacinarepository;

    public VacinaService(VacinaRepository vacinarepository) {
        this.vacinarepository = vacinarepository;
    }

    // POST
    @Transactional
    public VacinaResponseDTO criar(VacinaRequestDTO dto) {
        if (vacinarepository.existsByNome(dto.getNome())) {
            throw new BusinessRuleException("Nome de vacina já cadastrada");
        }

        Vacina vacina = new Vacina();
        vacina.setNome(dto.getNome());
        vacina.setDescricao(dto.getDescricao());
        vacina.setDuracao(dto.getDuracao());

        Vacina salva = vacinarepository.save(vacina);
        return toResponse(salva);
    }

    // GET ALL
    @Transactional(readOnly = true)
    public List<VacinaResponseDTO> listarTodas() {
        return vacinarepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    @Transactional(readOnly = true)
    public VacinaResponseDTO buscarPorId(Long idVacina) {
        Vacina vacina = vacinarepository.findById(idVacina)
                .orElseThrow(() -> new EntityNotFoundException("Vacina não encontrada: id=" + idVacina));

        return toResponse(vacina);
    }

    // PUT
    @Transactional
    public VacinaResponseDTO atualizar(Long idVacina, VacinaRequestDTO dto) {
        Vacina vacina = vacinarepository.findById(idVacina)
                .orElseThrow(() -> new EntityNotFoundException("Vacina não encontrada: id=" + idVacina));

        if (vacinarepository.existsByNomeAndIdVacinaNot(dto.getNome(), idVacina)) {
            throw new BusinessRuleException("Nome de vacina já cadastrada");
        }

        vacina.setNome(dto.getNome());
        vacina.setDescricao(dto.getDescricao());
        vacina.setDuracao(dto.getDuracao());

        Vacina atualizada = vacinarepository.save(vacina);
        return toResponse(atualizada);
    }

    // DELETE
    @Transactional
    public void deletar(Long idVacina) {
        if (!vacinarepository.existsById(idVacina)) {
            throw new EntityNotFoundException("Vacina não encontrada: id=" + idVacina);
        }
        vacinarepository.deleteById(idVacina);
    }

    // Conversão para DTO
    private VacinaResponseDTO toResponse(Vacina vacina) {
        return new VacinaResponseDTO(
                vacina.getIdVacina(),
                vacina.getNome(),
                vacina.getDescricao(),
                vacina.getDuracao()
        );
    }
}
