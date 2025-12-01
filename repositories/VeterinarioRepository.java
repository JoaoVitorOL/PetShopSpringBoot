package com.example.demo.repositories;

import com.example.demo.entities.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {


    boolean existsByCpf(String cpf);
    boolean existsByTelefone(String telefone);
    boolean existsByEmail(String email);


    boolean existsByCpfAndIdNot(String cpf, Long id);
    boolean existsByTelefoneAndIdNot(String telefone, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);

}