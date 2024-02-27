package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(CancelamentoConsultaDTO dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var horarioCancelamento = Duration.between(agora, consulta.getData()).toHours();

        if (horarioCancelamento < 24){
            throw new ValidacaoException("Horário de antecedencia minímo para o cancelamento é de 24h");
        }
    }
}
