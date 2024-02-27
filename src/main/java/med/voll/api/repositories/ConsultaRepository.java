package med.voll.api.repositories;

import med.voll.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long id, LocalDateTime data);
}
