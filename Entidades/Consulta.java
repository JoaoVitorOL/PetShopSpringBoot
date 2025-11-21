package com.example.petshop.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "consulta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_veterinario", nullable = false)
    private Veterinario veterinario;

    @ManyToMany
    @JoinTable(
        name = "consulta_vacina",
        joinColumns = @JoinColumn(name = "id_consulta"),
        inverseJoinColumns = @JoinColumn(name = "id_vacina")
    )
    private List<Vacina> vacinas = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @Column(nullable = false)
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status;

    public void addVacina(Vacina v) {
        if (!vacinas.contains(v)) {
            vacinas.add(v);
            v.getConsultas().add(this);
        }
    }
    public void removeVacina(Vacina v) {
        if (vacinas.remove(v)) {
            v.getConsultas().remove(this);
        }
    }
}
