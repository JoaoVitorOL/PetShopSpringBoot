package com.example.demo.entities;


import jakarta.persistence.*;
import java.util.List;
import com.example.demo.enums.Especialidade;

@Entity
@Table(name = "veterinario")
public class Veterinario extends Pessoa {

    @Column(nullable = false)
    private Double salario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "veterinario")
    private List<Consulta> consultas;

    public Veterinario() {}

    public Veterinario(Double salario, Especialidade especialidade) {
        this.salario = salario;
        this.especialidade = especialidade;
    }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public Especialidade getEspecialidade() { return especialidade; }
    public void setEspecialidade(Especialidade especialidade) { this.especialidade = especialidade; }

    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}
