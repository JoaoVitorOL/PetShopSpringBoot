package com.example.demo.enums;

public enum Especialidade {

    FELINOS(0, "Felinos"),
    CANINOS(1, "Caninos"),
    REPTIL(2, "Repteis"),
    AVES(3, "Aves"),
    ANIMALDOMESTICO(4, "Animal Doméstico"),
    ANIMALSILVESTRE(5, "Animal Silvestre"),
    INSETO(7, "Insetos"),
    ARACNIDEO(8, "Aracnídeos"),
    PECUARIA(9, "Animais de pecuária");

    private final int codigo;
    private final String descricao;

    Especialidade(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    // Retorna o enum correspondente ao código
    public static Especialidade toEnum(Integer cod) {
        if (cod == null) return null;
        for (Especialidade x : Especialidade.values()) {
            if (cod.equals(x.getCodigo())) return x;
        }
        throw new IllegalArgumentException("Especialidade inválida: " + cod);
    }

    // Retorna a descrição diretamente a partir do código
    public static String getDescricaoPorCodigo(Integer cod) {
        Especialidade e = toEnum(cod);
        return (e != null) ? e.getDescricao() : "Código inválido";
    }
}
