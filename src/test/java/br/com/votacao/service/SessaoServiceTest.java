package br.com.votacao.service;

import br.com.votacao.dto.request.AbrirSessaoRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService sessaoService;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private PautaService pautaService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(sessaoService, "duracaoDefaultMinutos", 1L);
    }


    @Test
    void abrirSessaoComDuracaoDefault_quandoNaoInformado() {

        Long pautaId = 1L;
        AbrirSessaoRequest request = new AbrirSessaoRequest(pautaId, null);

        Pauta pauta = Pauta.builder()
                .id(pautaId)
                .titulo("Teste Pauta")
                .descricao("Descricao Teste")
                .build();

        when(pautaService.buscarPorId(pautaId)).thenReturn(pauta);
        when(sessaoVotacaoRepository.findByPautaId(pautaId)).thenReturn(Optional.empty());
        when(sessaoVotacaoRepository.save(any())).thenReturn(new SessaoVotacao(1L, pauta,
                LocalDateTime.now().plusMinutes(10), LocalDateTime.now().plusMinutes(20), List.of()));


        SessaoVotacao resultado = sessaoService.abrirSessao(request);

        assertNotNull(resultado);
        assertEquals(pauta, resultado.getPauta());

    }

    @Test
    void buscarSessaoAtiva() {

        Long sessaoId = 1L;

        SessaoVotacao votacao = SessaoVotacao.builder()
                .id(sessaoId)
                .pauta(Pauta.builder().id(1L)
                .titulo("Pauta Teste").build())
                .dataAbertura(LocalDateTime.now().minusMinutes(1))
                .dataFechamento(LocalDateTime.now().plusMinutes(10))
                .votos(List.of())
                .build();

        when(sessaoVotacaoRepository.findById(sessaoId)).thenReturn(Optional.of(votacao));

        SessaoVotacao resultado = sessaoService.buscarSessaoAtiva(sessaoId);

        assertNotNull(resultado);
        assertEquals(sessaoId, resultado.getId());
        assertTrue(resultado.getDataFechamento().isAfter(LocalDateTime.now()));
    }

}