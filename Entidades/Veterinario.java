package Nome_do_Package;

import jakarta.persistence.*;

@Table(name = "Veterinario")
@Entity
public class Veterinario extends Pessoa {

    @Column(nullable = false)
    private Double salario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especialidade especialidade;

    public Veterinario() {}

    public Veterinario(Double salario, Especialidade especialidade) {
        this.salario = salario;
        this.especialidade = especialidade;
    }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public Especialidade getEspecialidade() { return especialidade; }
    public void setEspecialidade(Especialidade especialidade) { this.especialidade = especialidade; }
}
