package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamento {

    @Autowired
    private PacienteRepository repository;

    public void validador(DadosAgendamentoConsultaDTO dados){
        var pacienteAtivo = repository.findAtivoByID(dados.idMedico());
        if (!pacienteAtivo) {
            throw new ValidacaoException("Paciente inativo do nosso sistema.");
        }
    }
}
