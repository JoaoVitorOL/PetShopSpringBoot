package com.example.demo.domain.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

//import com.example.demo.domain.enums.Prioridade;
//import com.example.demo.domain.enums.Status;

@Entity
@Table(name = "Animal")
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true, length = 100)
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String especie;

    @Column(nullable = true, length = 150)
    private String raca;

    private LocalDate data_nascimento;

 //   @ManyToOne
    @JoinColumn(name = "id_dono", nullable = false)
    private Dono dono;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexoAnimal sexo;

    @Column(nullable = true)
    private Float peso;


    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }


    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }


    public LocalDate getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }


    public Integer getIdDono() {
        return id_dono;
    }

    public void setIdDono(Integer id_dono) {
        this.id_dono = id_dono;
    }

    public Enum getSexo() {
        return sexo;
    }

    public void setSexo(Enum sexo) {
        this.sexo = sexo;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

}
