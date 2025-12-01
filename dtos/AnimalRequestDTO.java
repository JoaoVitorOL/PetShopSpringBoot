package com.example.demo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.example.demo.enums.Sexo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;


public class AnimalRequestDTO {

    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Espécie é obrigatória")
    private String especie;


    private String raca;

    @NotNull(message = "A data de nascimento do animal é obrigatória. (se não souber faça estimativa")
    private LocalDate dataNascimento;

    @NotNull(message = "O id do dono é obrigatório.")
    @Positive(message = "O idDono deve ser um número positivo.")
    private Long idDono;

    @NotNull(message = "Sexo do animal é obrigatório")
    private Sexo sexo;

    @NotNull(message = "O peso do animal é obrigatório.")
    @Positive(message = "O peso do animal deve ser um número positivo.")
    private Float peso;

    // Getters e Setters

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