package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorConsultaDisponivelMedico implements ValidadorAgendamento{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validador (DadosAgendamentoConsultaDTO dados) {
        var medicoConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(),dados.data());
        if (medicoConsultaMesmoHorario){
            throw new ValidacaoException("Médico já está com consulta marcada.");
        }
    }
}
