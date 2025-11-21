package com.example.demo.enums;


public enum Especialidade{

    FELINOS(0, "Felinos"),
    CANINOS(1, "Caninos"),
    REPTIL(2, "Repteis"),
    AVES(3, "Aves"),
    ANIMALDOMESTICO(4, "Animal Domestico"),
    ANIMALSILVESTRE(5, "Animal Silvestre"),
    INSETO(7, "Insetos"),
    ARACNIDEO(7, "Aracnideos");


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

    public static Especialidade toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Especialidade x : Especialidade.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Especialidade inválida: " + cod);
    }
    }
