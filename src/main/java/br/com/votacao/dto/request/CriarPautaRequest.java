package br.com.votacao.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CriarPautaRequest {
    @NotBlank
    private String titulo;
    private String descricao;
}
