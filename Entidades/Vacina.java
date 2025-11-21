package com.example.petshop.entidades;

import jakarta.persistence.*;

@Table(name = "Vacina")
@Entity
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVacina;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    // Getters e Setters
    public Integer getIdVacina() { return idVacina; }
    public void setIdVacina(Integer idVacina) { this.idVacina = idVacina; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }
}
