package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VacinaRequestDTO {

    @NotBlank(message = "O nome da vacina é obrigatório")
    private String nome;

    private String descricao;

    @NotNull(message = "A duração da vacina é obrigatória")
    @Positive(message = "A duração deve ser um número positivo")
    private Integer duracao;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }
}
