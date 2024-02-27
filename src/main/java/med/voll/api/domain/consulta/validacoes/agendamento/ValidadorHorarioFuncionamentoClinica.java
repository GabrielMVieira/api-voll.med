package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamento{

    public void validador(DadosAgendamentoConsultaDTO dto){
        var dataConsulta = dto.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisFechamentoClinica){
            throw new ValidacaoException("Não é possivel marcar uma consulta nesse horário.");
        }
    }
}
