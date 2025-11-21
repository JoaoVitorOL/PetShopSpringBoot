package com.example.demo.domain.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String especie;

    @Column(length = 150)
    private String raca;

    private LocalDate dataNascimento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_dono", nullable = false)
    private Dono dono;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;


    private Float peso;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Consulta> consultas;

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public Dono getDono() { return dono; }
    public void setDono(Dono dono) { this.dono = dono; }

    public SexoAnimal getSexo() { return sexo; }
    public void setSexo(SexoAnimal sexo) { this.sexo = sexo; }

    public Float getPeso() { return peso; }
    public void setPeso(Float peso) { this.peso = peso; }

    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}
