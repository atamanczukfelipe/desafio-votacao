package br.com.votacao.service;

import br.com.votacao.dto.request.CriarPautaRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;

import org.checkerframework.checker.units.qual.C;
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

    @Test
    void criarPautaComSucesso(){

        CriarPautaRequest request = new CriarPautaRequest("Pauta Teste", "Teste Descrição");

        Pauta pautaMock = Pauta.builder()
                                .id(1L)
                                .titulo("Pauta Teste")
                                .descricao("Teste Descrição")
                                .build();

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaMock);

        Pauta result = pautaService.criarPauta(request);

        assertNotNull(result);
        assertEquals("Pauta Teste", result.getTitulo());
        verify(pautaRepository, times(1)).save(any());
    }

    @Test
    void buscarPorIdRetornaSucesso(){

        Long pautaId = 1L;
        Pauta pauta = new Pauta(pautaId,"Reforma", "Teste", null);

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pauta));

        Pauta resutlado = pautaService.buscarPorId(pautaId);

        assertNotNull(resutlado);
        assertEquals(pautaId,resutlado.getId());
    }

    @Test
    void buscarPorIdRetornaException(){

        Long pautaId = 100L;

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->{
            pautaService.buscarPorId(pautaId);
        });

        assertEquals("Pauta Não Encontrada: " + pautaId, ex.getMessage());
    }
}
