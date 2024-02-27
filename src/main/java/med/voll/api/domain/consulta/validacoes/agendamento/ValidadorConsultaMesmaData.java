package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorConsultaMesmaData implements ValidadorAgendamento{

    @Autowired
    private ConsultaRepository repository;

    public void validador(DadosAgendamentoConsultaDTO dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacienteComConsultaMarcadanoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacienteComConsultaMarcadanoDia) {
            throw new ValidacaoException("Paciente com consulta já marcada nesse dia:" + LocalDate.now().getDayOfMonth());
        }
    }
}
