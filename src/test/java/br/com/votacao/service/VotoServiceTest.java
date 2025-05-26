package br.com.votacao.service;

import br.com.votacao.dto.request.VotoRequest;
import br.com.votacao.dto.response.ResultadoVotacaoResponce;
import br.com.votacao.model.*;
import br.com.votacao.repository.SessaoVotacaoRepository;
import br.com.votacao.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private SessaoService sessaoService;

    @Test
    void registrarVotoComSucesso(){

        Long sessaoId = 1L;
        String CPF = "12345677";
        VotoRequest request = new VotoRequest(sessaoId, CPF, OpcaoVoto.SIM);

        SessaoVotacao sessao = SessaoVotacao.builder()
                                            .id(sessaoId)
                                            .dataAbertura(LocalDateTime.now().minusMinutes(1))
                                            .dataAbertura(LocalDateTime.now().plusMinutes(20))
                                            .pauta(Pauta.builder().id(1L).titulo("Teste").build())
                                            .build();

        when(sessaoService.buscarSessaoAtiva(sessaoId)).thenReturn(sessao);
        when(votoRepository.existsByCpfAssociadoAndSessaoId(CPF, sessaoId)).thenReturn(false);

        votoService.registrarVoto(request);

        verify(votoRepository, times(1)).save(any(Voto.class));
    }

    @Test
    void registrarVotoQuandoCpfJaVotou(){

        Long sessaoId = 1L;
        String CPF = "12345677";
        VotoRequest request = new VotoRequest(sessaoId, CPF, OpcaoVoto.NAO);

        SessaoVotacao sessao = SessaoVotacao.builder()
                                            .id(sessaoId)
                                            .dataAbertura(LocalDateTime.now().minusMinutes(1))
                                            .dataAbertura(LocalDateTime.now().plusMinutes(20))
                                            .pauta(Pauta.builder().id(1L).titulo("Teste").build())
                                            .build();

        when(sessaoService.buscarSessaoAtiva(sessaoId)).thenReturn(sessao);
        when(votoRepository.existsByCpfAssociadoAndSessaoId(CPF, sessaoId)).thenReturn(true);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () ->{
            votoService.registrarVoto(request);
        });

        assertEquals("CPF já votou nesta sessão", ex.getMessage());
        verify(votoRepository, never()).save(any());
    }

    @Test
    void apurarResultadoComContagemCorreta(){

        Long pautaId = 10L;
        Long sessaoId = 20L;


        Pauta pauta = Pauta.builder()
                            .id(pautaId)
                            .titulo("Pauta Teste")
                            .build();

        SessaoVotacao sessao = SessaoVotacao.builder()
                                            .id(sessaoId)
                                            .pauta(pauta)
                                            .dataAbertura(LocalDateTime.now().minusMinutes(5))
                                            .dataFechamento(LocalDateTime.now().plusMinutes(5))
                                            .build();

        List<Voto> votos = List.of(
                Voto.builder().voto(OpcaoVoto.SIM).build(),
                Voto.builder().voto(OpcaoVoto.SIM).build(),
                Voto.builder().voto(OpcaoVoto.NAO).build()
        );

        when(sessaoVotacaoRepository.findByPautaId(pautaId)).thenReturn(Optional.of(sessao));
        when(votoRepository.findAllBySessaoId(sessaoId)).thenReturn(votos);

        ResultadoVotacaoResponce resultado = votoService.apurarResultado(pautaId);

        assertNotNull(resultado);
        assertEquals(pautaId, resultado.getPautaId());
        assertEquals("Pauta Teste", resultado.getTitulo());
        assertEquals(2, resultado.getTotalSim());
        assertEquals(1, resultado.getTotalNao());
        assertFalse(resultado.isEncerrada());
    }
}
