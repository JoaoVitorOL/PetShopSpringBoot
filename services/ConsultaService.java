package com.example.demo.services;


import com.example.demo.exceptions.BusinessRuleException;
import com.example.demo.dtos.ConsultaRequestDTO;
import com.example.demo.dtos.ConsultaResponseDTO;
import com.example.demo.entities.Animal;
import com.example.demo.entities.Consulta;
import com.example.demo.entities.Vacina;
import com.example.demo.entities.Veterinario;
import com.example.demo.enums.Status;
import com.example.demo.repositories.AnimalRepository;
import com.example.demo.repositories.ConsultaRepository;
import com.example.demo.repositories.VacinaRepository;
import com.example.demo.repositories.VeterinarioRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final AnimalRepository animalRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final VacinaRepository vacinaRepository;

    // Injeta automaticamente todos os repositórios necessários
    public ConsultaService(
            ConsultaRepository consultaRepository,
            AnimalRepository animalRepository,
            VeterinarioRepository veterinarioRepository,
            VacinaRepository vacinaRepository
    ) {
        this.consultaRepository = consultaRepository;
        this.animalRepository = animalRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.vacinaRepository = vacinaRepository;
    }

    // ------------------------------------------------------------
    // CRIAR CONSULTA
    // ------------------------------------------------------------
    @Transactional
    public ConsultaResponseDTO criar(ConsultaRequestDTO dto) {

        Animal animal = animalRepository.findById(dto.getIdAnimal())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));

        Veterinario veterinario = veterinarioRepository.findById(dto.getIdVeterinario())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));

        Consulta consulta = new Consulta();
        consulta.setAnimal(animal);
        consulta.setVeterinario(veterinario);
        consulta.setDataConsulta(dto.getDataConsulta());
        consulta.setMotivo(dto.getMotivo());
        consulta.setStatus(dto.getStatus());

        // Vacinas opcionais
        if (dto.getVacinasIds() != null && !dto.getVacinasIds().isEmpty()) {
            List<Vacina> vacinas = vacinaRepository.findAllById(dto.getVacinasIds());
            vacinas.forEach(consulta::addVacina);
        }

        Consulta salva = consultaRepository.save(consulta);
        return montarResponse(salva);
    }

    // ------------------------------------------------------------
    // BUSCAR POR ID
    // ------------------------------------------------------------
    @Transactional(readOnly = true)
    public ConsultaResponseDTO buscarPorId(Long id) {
        Consulta c = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));
        return montarResponse(c);
    }

    // ------------------------------------------------------------
    // LISTAR TODAS
    // ------------------------------------------------------------
    @Transactional(readOnly = true)
    public List<ConsultaResponseDTO> listar() {
        return consultaRepository.findAll().stream()
                .map(this::montarResponse)
                .toList();
    }

    // ------------------------------------------------------------
    // ATUALIZAR CONSULTA
    // ------------------------------------------------------------
    @Transactional
    public ConsultaResponseDTO atualizar(Long id, ConsultaRequestDTO dto) {

        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

        Animal animal = animalRepository.findById(dto.getIdAnimal())
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado."));

        Veterinario veterinario = veterinarioRepository.findById(dto.getIdVeterinario())
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado."));

        consulta.setAnimal(animal);
        consulta.setVeterinario(veterinario);
        consulta.setDataConsulta(dto.getDataConsulta());
        consulta.setMotivo(dto.getMotivo());
        consulta.setStatus(dto.getStatus());

        // Resetar lista e adicionar novas vacinas
        consulta.getVacinas().clear();

        if (dto.getVacinasIds() != null && !dto.getVacinasIds().isEmpty()) {
            List<Vacina> vacinas = vacinaRepository.findAllById(dto.getVacinasIds());
            vacinas.forEach(consulta::addVacina);
        }

        Consulta atualizada = consultaRepository.save(consulta);
        return montarResponse(atualizada);
    }

    // ------------------------------------------------------------
// REMOVER CONSULTA
// ------------------------------------------------------------
    @Transactional
    public void deletar(Long id) {

        // 1. Buscar consulta
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada."));

        // 2. Validar status não permitido para exclusão
        if (consulta.getStatus() == Status.AGENDADA
                || consulta.getStatus() == Status.EM_ATENDIMENTO
                || consulta.getStatus() == Status.REMARCADA
                || consulta.getStatus() == Status.EM_ANALISE_EXAMES) {

            throw new BusinessRuleException(
                    "A consulta não pode ser excluída porque possui status " + consulta.getStatus() + " que impede exclusão."
            );
        }

        // 3. Excluir
        consultaRepository.delete(consulta);
    }


    // ------------------------------------------------------------
    // MÉTODO PRIVADO PARA MONTAR O DTO DE RESPOSTA
    // ------------------------------------------------------------
    private ConsultaResponseDTO montarResponse(Consulta c) {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();

        dto.setIdConsulta(c.getIdConsulta());
        dto.setIdAnimal(c.getAnimal().getId());
        dto.setNomeAnimal(c.getAnimal().getNome());
        dto.setIdVeterinario(c.getVeterinario().getId());
        dto.setNomeVeterinario(c.getVeterinario().getNome());
        dto.setDataConsulta(c.getDataConsulta());
        dto.setMotivo(c.getMotivo());
        dto.setStatus(c.getStatus());

        // ---------------------------------
        // NOVO: incluir vacinas no response
        // ---------------------------------
        dto.setVacinasIds(
                c.getVacinas()
                        .stream()
                        .map(Vacina::getIdVacina)
                        .toList()
        );

        dto.setVacinas(
                c.getVacinas()
                        .stream()
                        .map(Vacina::getNome)
                        .toList()
        );

        return dto;
    }
}