package com.example.demo.service;

import com.example.demo.entities.Dono;
import com.example.demo.dtos.DonoRequestDTO;
import com.example.demo.dtos.DonoResponseDTO;
import com.example.demo.repositories.DonoRepository;
import com.example.demo.enums.CidadeSC;
import com.example.demo.enums.Sexo;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.exceptions.BusinessRuleException;


import java.util.List;

@Service
public class DonoService {

    private final DonoRepository donoRepository;

    public DonoService(DonoRepository donoRepository) {
        this.donoRepository = donoRepository;
    }

    // -------------------- GET (LISTAR) --------------------
    @Transactional(readOnly = true)
    public List<DonoResponseDTO> listarTodos() {
        return donoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // -------------------- GET (BUSCAR POR ID) --------------------
    @Transactional(readOnly = true)
    public DonoResponseDTO buscarPorId(Long id) {
        Dono dono = donoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dono não encontrado"));
        return toDTO(dono);
    }

    // -------------------- POST (CRIAR) --------------------
    @Transactional
    public DonoResponseDTO criar(DonoRequestDTO dto) {

        // Valida CPF único
        if (donoRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessRuleException("CPF já cadastrado");
        }

        // Valida telefone único
        if (donoRepository.existsByTelefone(dto.getTelefone())) {
            throw new BusinessRuleException("Telefone já cadastrado");
        }

        // Valida email único
        if (donoRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessRuleException("Email já cadastrado");
        }

        Dono dono = new Dono();

        dono.setSexo(Sexo.toEnum(dto.getSexo()));
        dono.setNome(dto.getNome());
        dono.setSobrenome(dto.getSobrenome());
        dono.setCpf(dto.getCpf());
        dono.setTelefone(dto.getTelefone());
        dono.setEmail(dto.getEmail());
        dono.setSenha(dto.getSenha());
        dono.setCidade(CidadeSC.toEnum(dto.getCidade()));
        dono.setEndereco(dto.getEndereco());

        donoRepository.save(dono);

        return toDTO(dono);
    }

    // -------------------- PUT (ATUALIZAR) --------------------
    @Transactional
    public DonoResponseDTO atualizar(Long id, DonoRequestDTO dto) {

        Dono dono = donoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dono não encontrado"));

        // Validação de unicidade ignorando o próprio ID (necessário no PUT)

        if (donoRepository.existsByCpfAndIdNot(dto.getCpf(), id)) {
            throw new BusinessRuleException("CPF já cadastrado");
        }

        if (donoRepository.existsByTelefoneAndIdNot(dto.getTelefone(), id)) {
            throw new BusinessRuleException("Telefone já cadastrado");
        }

        if (donoRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new BusinessRuleException("Email já cadastrado");
        }

        dono.setSexo(Sexo.toEnum(dto.getSexo()));
        dono.setNome(dto.getNome());
        dono.setSobrenome(dto.getSobrenome());
        dono.setCpf(dto.getCpf());
        dono.setTelefone(dto.getTelefone());
        dono.setEmail(dto.getEmail());
        dono.setSenha(dto.getSenha());
        dono.setCidade(CidadeSC.toEnum(dto.getCidade()));
        dono.setEndereco(dto.getEndereco());

        return toDTO(donoRepository.save(dono));
    }

    // -------------------- DELETE (DELETAR) --------------------
    @Transactional
    public void deletar(Long id) {
        if (!donoRepository.existsById(id)) {
            throw new EntityNotFoundException("Dono não encontrado");
        }
        donoRepository.deleteById(id);
    }

    // Conversor para DTO
    private DonoResponseDTO toDTO(Dono dono) {
        return new DonoResponseDTO(
                dono.getId(),
                dono.getNome(),
                dono.getEmail(),
                dono.getSexo(),
                dono.getCidade(),
                dono.getEndereco()
        );
    }
}
