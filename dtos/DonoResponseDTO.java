package com.example.demo.dtos;

import com.example.demo.enums.Sexo;
import com.example.demo.enums.CidadeSC;

public class DonoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String sexo;
    private String cidade;
    private String endereco;

    public DonoResponseDTO(Long id,
                           String nome,
                           String email,
                           Sexo sexo,
                           CidadeSC cidade,
                           String endereco) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.sexo = sexo.getDescricao();
        this.cidade = cidade.getDescricao();
        this.endereco = endereco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
