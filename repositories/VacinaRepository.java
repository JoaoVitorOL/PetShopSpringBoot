package com.example.demo.repositories;

import com.example.demo.entities.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina,Long> {

    // Verificação básica (POST)
    boolean existsByNome(String nome);

    // Verificação ignorando o ID atual (PUT)
    boolean existsByNomeAndIdVacinaNot(String nome, Long idVacina);

}