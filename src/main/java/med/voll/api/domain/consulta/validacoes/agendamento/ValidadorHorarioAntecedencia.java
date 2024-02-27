package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento{

    public void validador(DadosAgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferençaDeMinutos = Duration.between(dataConsulta,dataAgora).toMinutes();

        if(diferençaDeMinutos < 30 ){
            throw new ValidacaoException("Horário de antecedência mínima é de 30 minutos.");
        }
    }
}
