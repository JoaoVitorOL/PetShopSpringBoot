package com.example.demo.dtos;

public class VacinaResponseDTO {

    private Long idVacina;
    private String nome;
    private String descricao;
    private Integer duracao;

    public VacinaResponseDTO() {}

    public VacinaResponseDTO(Long idVacina, String nome, String descricao, Integer duracao) {
        this.idVacina = idVacina;
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
    }

    // Getters e Setters
    public Long getIdVacina() {
        return idVacina;
    }

    public void setIdVacina(Long idVacina) {
        this.idVacina = idVacina;
    }

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
