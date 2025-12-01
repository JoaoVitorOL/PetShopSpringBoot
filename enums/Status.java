package com.example.demo.enums;

public enum Status {

    AGENDADA(0, "Agendada"),
    EM_ATENDIMENTO(1, "Em atendimento"),
    REALIZADA(2, "Realizada"),
    CANCELADA(3, "Cancelada"),
    NAO_COMPARECEU(4, "Não compareceu"),
    REMARCADA(5, "Remarcada"),
    EM_ANALISE_EXAMES(6, "Em análise de exames");

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

    // Retorna o enum correspondente ao código
    public static Status toEnum(Integer cod) {
        if (cod == null) return null;
        for (Status x : Status.values()) {
            if (cod.equals(x.getCodigo())) return x;
        }
        throw new IllegalArgumentException("Status inválido: " + cod);
    }

    // Retorna a descrição diretamente a partir do código
    public static String getDescricaoPorCodigo(Integer cod) {
        Status s = toEnum(cod);
        return (s != null) ? s.getDescricao() : "Código inválido";
    }
}
