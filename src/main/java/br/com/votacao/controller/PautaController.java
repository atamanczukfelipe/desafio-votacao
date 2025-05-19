package br.com.votacao.controller;

import br.com.votacao.dto.request.CriarPautaRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/pautas")
public class PautaController {
    
    @Autowired
    private PautaService pautaService;

    @PostMapping 
    public ResponseEntity<Pauta> criarPauta(@Validated @RequestBody CriarPautaRequest request) {
        Pauta pauta = pautaService.criarPauta(request);
        return ResponseEntity.ok(pauta);
    }
}
