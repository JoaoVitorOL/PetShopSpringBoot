package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "vacina")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVacina;

    @Column(nullable = false, unique= true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    @ManyToMany(mappedBy = "vacinas")
    private List<Consulta> consultas = new ArrayList<>();

    // Getters e Setters
    public Long getIdVacina() { return idVacina; }
    public void setIdVacina(Long idVacina) { this.idVacina = idVacina; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }

    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}