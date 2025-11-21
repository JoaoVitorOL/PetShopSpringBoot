package Nome_do_Package;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "dono")
public class Dono extends Pessoa {

    @Enumerated(EnumType.STRING)
    private CidadeSC cidade;

    private String endereco;

    @OneToMany(mappedBy = "dono")
    private List<Animal> animais;

    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public List<Animal> getAnimais() { return animais; }
    public void setAnimais(List<Animal> animais) { this.animais = animais; }
}
