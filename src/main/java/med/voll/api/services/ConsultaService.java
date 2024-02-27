package med.voll.api.services;

import med.voll.api.domain.consulta.*;
import med.voll.api.domain.consulta.validacoes.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamento;
import med.voll.api.domain.consulta.validacoes.cancelamento.CancelamentoConsultaDTO;
import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.repositories.PacienteRepository;
import med.voll.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamento> validacoes;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosRequestDTO agendar (DadosAgendamentoConsultaDTO dados){
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }
        if (medicoRepository != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe!");
        }

        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        this.validacoes.forEach(v -> v.validador(dados));

        return new DadosRequestDTO(consulta);
    }

    public void cancelar(CancelamentoConsultaDTO dto){
        if (!consultaRepository.existsById(dto.idConsulta())){
            throw new ValidacaoException("ID nao existe.");
        }

        this.validadoresCancelamento.forEach(v -> v.validar(dto));

        var consulta = consultaRepository.getReferenceById(dto.idConsulta());
        consulta.cancelar(dto.cancelamento());
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
