package br.com.votacao.dto.request;

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
    private String voto;
}