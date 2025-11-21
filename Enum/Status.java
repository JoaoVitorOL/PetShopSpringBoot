package com.petshop.demo.domain.enums;

public enum StatusConsulta {

    AGENDADA(0, "Agendada"),
    EM_ATENDIMENTO(2, "Em atendimento"),
    REALIZADA(3, "Realizada"),
    CANCELADA(4, "Cancelada"),
    NAO_COMPARECEU(5, "Não compareceu"),
    REMARCADA(6, "Remarcada"),
    EM_ANALISE_EXAMES(7, "Em análise de exames"),


    private final int codigo;
    private final String descricao;

    StatusConsulta(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusConsulta toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (StatusConsulta x : StatusConsulta.values()) {
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Status inválido: " + cod);
    }
}

