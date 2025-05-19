package br.com.votacao.controller;

import br.com.votacao.dto.request.AbrirSessaoRequest;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessoes")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;

    @PostMapping
    public ResponseEntity<SessaoVotacao> abrirSessao(@Validated @RequestBody AbrirSessaoRequest request) {
        SessaoVotacao sessao = sessaoService.abrirSessao(request);
        return ResponseEntity.ok(sessao);
    }
}