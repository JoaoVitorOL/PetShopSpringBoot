package com.example.demo.dtos;

import com.example.demo.entities.Veterinario;
import com.example.demo.enums.Sexo;
import com.example.demo.enums.Especialidade;
import java.io.Serializable;

public class VeterinarioResponseDTO implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private Double salario;
    private String sexo;          // descrição legível
    private String especialidade; // descrição legível

    public VeterinarioResponseDTO() {}

    // Construtor de conveniência para montar DTO a partir da entidade
    public VeterinarioResponseDTO(Veterinario v) {
        this.id = v.getId();
        this.nome = v.getNome();
        this.email = v.getEmail();
        this.salario = v.getSalario();

        Sexo s = v.getSexo(); // assume Pessoa.getSexo() retorna Sexo por toEnum
        this.sexo = (s != null) ? s.getDescricao() : null;

        Especialidade e = v.getEspecialidade();
        this.especialidade = (e != null) ? e.getDescricao() : null;
    }

    // GETTERS / SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}
