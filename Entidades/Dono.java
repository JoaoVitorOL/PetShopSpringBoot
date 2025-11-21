package Nome_do_Package;

import jakarta.persistence.*;

@Entity
public class Dono extends Pessoa {

    @Enumerated(EnumType.STRING)
    private Cidade cidade;

    private String endereco;

    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
