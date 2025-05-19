package br.com.votacao.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoVotacaoResponce {
    private Long pautaId;
    private String titulo;
    private long totalSim;
    private long totalNao;
    private boolean encerrada;
}