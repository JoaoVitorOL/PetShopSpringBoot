package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.List;
import com.example.demo.enums.CidadeSC;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CidadeSC cidade;

    private String endereco;

    @OneToMany(mappedBy = "dono")
    private List<Animal> animais;

    public CidadeSC getCidade() { return cidade; }
    public void setCidade(CidadeSC cidade) { this.cidade = cidade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Animal> getAnimais() { return animais; }
    public void setAnimais(List<Animal> animais) { this.animais = animais; }
}
