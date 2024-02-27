package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamento{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validador (DadosAgendamentoConsultaDTO dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoByID(dados.idMedico());
        if (!medicoAtivo) {
            throw new ValidacaoException("Médico está inativo no nosso sistema");
        }
    }
}
