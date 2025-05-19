package br.com.votacao.controller;

import br.com.votacao.dto.response.ResultadoVotacaoResponce;
import br.com.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadoController {

    @Autowired
    private VotoService votoService;

    @GetMapping("/{pautaId}")
    public ResponseEntity<ResultadoVotacaoResponce> obterResultado(@PathVariable Long pautaId) {
        ResultadoVotacaoResponce resultado = votoService.apurarResultado(pautaId);
        return ResponseEntity.ok(resultado);
    }
}
