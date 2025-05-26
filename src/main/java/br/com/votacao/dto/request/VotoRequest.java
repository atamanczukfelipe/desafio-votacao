package br.com.votacao.dto.request;

import br.com.votacao.model.OpcaoVoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VotoRequest {
    @NotNull
    private Long sessaoId;

    @NotBlank
    private String cpfAssociado;

    @NotNull
    private OpcaoVoto voto;

    public VotoRequest(Long sessaoId, String cpfAssociado, OpcaoVoto voto){
        this.sessaoId = sessaoId;
        this.cpfAssociado = cpfAssociado;
        this.voto = voto;
    }
}