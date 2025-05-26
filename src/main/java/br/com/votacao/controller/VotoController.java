package br.com.votacao.controller;

import br.com.votacao.dto.request.VotoRequest;
import br.com.votacao.repository.VotoRepository;
import br.com.votacao.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/votos")
@RequiredArgsConstructor
public class VotoController {


    private final VotoService votoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void votar(@Validated @RequestBody VotoRequest request) {
        votoService.registrarVoto(request);
    }
}