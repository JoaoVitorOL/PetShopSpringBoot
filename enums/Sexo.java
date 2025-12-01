package com.example.demo.enums;

public enum Sexo {

    MASCULINO(0, "Masculino"),
    FEMININO(1, "Feminino");

    private final int codigo;
    private final String descricao;

    Sexo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Sexo toEnum(Integer cod) {
        if (cod == null) return null;

        for (Sexo s : Sexo.values()) {
            if (cod.equals(s.getCodigo())) return s;
        }
        throw new IllegalArgumentException("Sexo inválido: " + cod);
    }
}
