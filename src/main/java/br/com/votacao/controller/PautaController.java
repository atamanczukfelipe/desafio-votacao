package br.com.votacao.controller;

import br.com.votacao.dto.request.CriarPautaRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pautas")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPauta(@Validated @RequestBody CriarPautaRequest request) {
        pautaService.criarPauta(request);
    }
}
