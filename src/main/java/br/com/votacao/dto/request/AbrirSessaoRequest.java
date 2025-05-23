package br.com.votacao.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AbrirSessaoRequest {
    @NotNull
    private Long pautaId;
    private Long duracaoEmMinutos;

    public AbrirSessaoRequest(Long pautaId, Long duracaoEmMinutos){
        this.pautaId = pautaId;
        this.duracaoEmMinutos = duracaoEmMinutos;
    }
}
