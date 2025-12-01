package com.example.demo.repositories;

import com.example.demo.entities.Dono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Long> {

    // Verificação básica (POST)
    boolean existsByCpf(String cpf);
    boolean existsByTelefone(String telefone);
    boolean existsByEmail(String email);

    // Verificação ignorando o ID atual (PUT)
    boolean existsByCpfAndIdNot(String cpf, Long id);
    boolean existsByTelefoneAndIdNot(String telefone, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);

}