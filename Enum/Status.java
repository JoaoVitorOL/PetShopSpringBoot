package com.example.demo.enums;

public enum Status {

    AGENDADA(0, "Agendada"),
    EM_ATENDIMENTO(2, "Em atendimento"),
    REALIZADA(3, "Realizada"),
    CANCELADA(4, "Cancelada"),
    NAO_COMPARECEU(5, "Não compareceu"),
    REMARCADA(6, "Remarcada"),
    EM_ANALISE_EXAMES(7, "Em análise de exames");


    private final int codigo;
    private final String descricao;

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (Status x : Status.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + cod);
    }
    }
