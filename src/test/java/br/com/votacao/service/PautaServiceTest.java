package br.com.votacao.service;

import br.com.votacao.dto.request.CriarPautaRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService pautaService;


    @Mock
    private PautaRepository pautaRepository;

}
