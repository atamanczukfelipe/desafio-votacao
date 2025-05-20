package br.com.votacao.controller;

import br.com.votacao.dto.request.VotoRequest;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<String> votar(@Validated @RequestBody VotoRequest request) {
        votoService.registrarVoto(request);
        return ResponseEntity.ok("Voto computado com Sucesso");
    }
}