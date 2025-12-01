package com.example.demo.enums;

public enum CidadeSC {

    FLORIANOPOLIS(0, "Florianópolis"),
    JOINVILLE(1, "Joinville"),
    BLUMENAU(2, "Blumenau"),
    SAO_JOSE(3, "São José"),
    CHAPECO(4, "Chapecó"),
    ITAJAI(5, "Itajaí"),
    CRICIUMA(6, "Criciúma"),
    JARAGUA_DO_SUL(7, "Jaraguá do Sul"),
    PALHOCA(8, "Palhoça"),
    LAGES(9, "Lages"),
    BALNEARIO_CAMBORIU(10, "Balneário Camboriú"),
    BRUSQUE(11, "Brusque"),
    TUBARAO(12, "Tubarão"),
    SAO_BENTO_DO_SUL(13, "São Bento do Sul"),
    RIO_DO_SUL(14, "Rio do Sul"),
    CAMBORIU(15, "Camboriú"),
    GASPAR(16, "Gaspar"),
    ARARANGUA(17, "Araranguá"),
    BIGUACU(18, "Biguaçu"),
    CONCORDIA(19, "Concórdia"),
    IMBITUBA(20, "Imbituba"),
    ITAPEMA(21, "Itapema"),
    MAFRA(22, "Mafra"),
    CANOINHAS(23, "Canoinhas"),
    VIDEIRA(24, "Videira"),
    CACADOR(25, "Caçador"),
    CAMPOS_NOVOS(26, "Campos Novos"),
    XANXERE(27, "Xanxerê"),
    TIJUCAS(28, "Tijucas"),
    FRAIBURGO(29, "Fraiburgo"),
    PENHA(30, "Penha"),
    PORTO_BELO(31, "Porto Belo"),
    ICARA(32, "Içara"),
    SOMBRIO(33, "Sombrio"),
    ORLEANS(34, "Orleans"),
    SAO_MIGUEL_DO_OESTE(35, "São Miguel do Oeste"),
    CURITIBANOS(36, "Curitibanos"),
    LAGUNA(37, "Laguna");

    private final int codigo;
    private final String descricao;

    CidadeSC(int codigo, String descricao) {
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
    public static CidadeSC toEnum(Integer cod) {
        if (cod == null) return null;
        for (CidadeSC x : CidadeSC.values()) {
            if (cod.equals(x.getCodigo())) return x;
        }
        throw new IllegalArgumentException("CidadeSC inválida: " + cod);
    }
}
