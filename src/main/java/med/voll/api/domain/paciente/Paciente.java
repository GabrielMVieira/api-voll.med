package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void exclui() {
        this.ativo = false;
    }

    public void atualizaCadastro(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizaEndereco(dados.endereco());
        }
    }
}
