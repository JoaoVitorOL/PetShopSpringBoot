package NOME DO PACOTE;

import jakarta.persistence.*;

@Entity
public class Dono extends Pessoa {
    
    @Enumerated(EnumType.STRING)
    private Cidade cidade;

    private String endereco;

    // Getters e setters
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
