package br.com.votacao.controller;

import br.com.votacao.dto.request.AbrirSessaoRequest;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sessoes")
@RequiredArgsConstructor
public class SessaoController {


    private final SessaoService sessaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void abrirSessao(@Validated @RequestBody AbrirSessaoRequest request) {
        sessaoService.abrirSessao(request);
    }
}