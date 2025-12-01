package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.enums.Sexo;


public class AnimalResponseDTO {

    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private Long idDono;
    private String nomeDono;
    private Sexo sexo;
    private Float peso;


    // CONSTRUTOR COMPLETO
    public AnimalResponseDTO(
            Long id,
            String nome,
            String especie,
            String raca,
            LocalDate dataNascimento,
            Long idDono,
            String nomeDono,
            Sexo sexo,
            Float peso
    ) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.dataNascimento = dataNascimento;
        this.idDono = idDono;
        this.nomeDono = nomeDono;
        this.sexo = sexo;
        this.peso = peso;

    }
    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getIdDono() {
        return idDono;
    }

    public void setIdDono(Long idDono) {
        this.idDono = idDono;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }


}
