package com.example.demo.service;

import com.example.demo.dtos.VeterinarioRequestDTO;
import com.example.demo.dtos.VeterinarioResponseDTO;
import com.example.demo.entities.Veterinario;
import com.example.demo.enums.Especialidade;
import com.example.demo.enums.Sexo;
import com.example.demo.repositories.VeterinarioRepository;
import com.example.demo.exceptions.BusinessRuleException;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    // -------------------- GET (LISTAR TODOS) --------------------
    @Transactional(readOnly = true)
    public List<VeterinarioResponseDTO> listarTodos() {
        return veterinarioRepository.findAll()
                .stream()
                .map(VeterinarioResponseDTO::new)
                .toList();
    }

    // -------------------- GET (BUSCAR POR ID) --------------------
    @Transactional(readOnly = true)
    public VeterinarioResponseDTO buscarPorId(Long id) {
        Veterinario v = veterinarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado"));

        return new VeterinarioResponseDTO(v);
    }

    // -------------------- POST (CRIAR) --------------------
    @Transactional
    public VeterinarioResponseDTO criar(VeterinarioRequestDTO dto) {

        // Validação de unicidade
        if (veterinarioRepository.existsByCpf(dto.getCpf())) {
            throw new BusinessRuleException("CPF já cadastrado");
        }

        if (veterinarioRepository.existsByTelefone(dto.getTelefone())) {
            throw new BusinessRuleException("Telefone já cadastrado");
        }

        if (veterinarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessRuleException("Email já cadastrado");
        }

        Veterinario v = new Veterinario();

        v.setNome(dto.getNome());
        v.setSobrenome(dto.getSobrenome());
        v.setCpf(dto.getCpf());
        v.setTelefone(dto.getTelefone());
        v.setEmail(dto.getEmail());
        v.setSenha(dto.getSenha());
        v.setSexo(Sexo.toEnum(dto.getSexo()));

        v.setSalario(dto.getSalario());
        v.setEspecialidade(Especialidade.toEnum(dto.getEspecialidade()));

        veterinarioRepository.save(v);

        return new VeterinarioResponseDTO(v);
    }

    // -------------------- PUT (ATUALIZAR) --------------------
    @Transactional
    public VeterinarioResponseDTO atualizar(Long id, VeterinarioRequestDTO dto) {

        Veterinario v = veterinarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Veterinário não encontrado"));

        // Validação ignorando o próprio registro
        if (veterinarioRepository.existsByCpfAndIdNot(dto.getCpf(), id)) {
            throw new BusinessRuleException("CPF já cadastrado");
        }

        if (veterinarioRepository.existsByTelefoneAndIdNot(dto.getTelefone(), id)) {
            throw new BusinessRuleException("Telefone já cadastrado");
        }

        if (veterinarioRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new BusinessRuleException("Email já cadastrado");
        }

        v.setNome(dto.getNome());
        v.setSobrenome(dto.getSobrenome());
        v.setCpf(dto.getCpf());
        v.setTelefone(dto.getTelefone());
        v.setEmail(dto.getEmail());
        v.setSenha(dto.getSenha());
        v.setSexo(Sexo.toEnum(dto.getSexo()));

        v.setSalario(dto.getSalario());
        v.setEspecialidade(Especialidade.toEnum(dto.getEspecialidade()));

        veterinarioRepository.save(v);

        return new VeterinarioResponseDTO(v);
    }

    // -------------------- DELETE (DELETAR) --------------------
    @Transactional
    public void deletar(Long id) {
        if (!veterinarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Veterinário não encontrado");
        }
        veterinarioRepository.deleteById(id);
    }
}
