package com.example.demo.dtos;

import jakarta.validation.constraints.*;
import java.io.Serializable;

public class VeterinarioRequestDTO implements Serializable {

    @NotNull(message = "O sexo é obrigatório")
    private Integer sexo;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter 10 ou 11 dígitos numéricos")
    private String telefone;

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotNull(message = "O salário é obrigatório.")
    @Positive(message = "O salário deve ser positivo.")
    private Double salario;

    @NotNull(message = "A especialidade é obrigatória.")
    private Integer especialidade;

    // GETTERS e SETTERS simples (DTO recebe inteiros)
    public Integer getSexo() { return sexo; }
    public void setSexo(Integer sexo) { this.sexo = sexo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public Integer getEspecialidade() { return especialidade; }
    public void setEspecialidade(Integer especialidade) { this.especialidade = especialidade; }
}
