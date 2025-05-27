package br.com.votacao.controller;

import br.com.votacao.dto.response.ResultadoVotacaoResponce;
import br.com.votacao.service.VotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResultadoController.class)
class ResultadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoService votoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obterResultadoOK() throws  Exception{
        Long pautaId = 1L;

        ResultadoVotacaoResponce mockResultado = ResultadoVotacaoResponce.builder()
                                                                         .pautaId(pautaId).titulo("Teste de Pauta")
                                                                         .totalSim(3L)
                                                                         .totalNao(2L)
                                                                         .encerrada(true)
                                                                         .build();

        when(votoService.apurarResultado(pautaId)).thenReturn(mockResultado);

        mockMvc.perform(get("/api/v1/resultados/{pautaId}", pautaId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pautaId").value(pautaId))
                .andExpect(jsonPath("$.titulo").value("Teste de Pauta"))
                .andExpect(jsonPath("$.totalSim").value(3))
                .andExpect(jsonPath("$.totalNao").value(2))
                .andExpect(jsonPath("$.encerrada").value(true));
    }
}
