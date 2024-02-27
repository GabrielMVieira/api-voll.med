package med.voll.api.domain.consulta.validacoes.cancelamento;

import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento cancelamento
) {
}
