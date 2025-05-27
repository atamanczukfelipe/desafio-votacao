package br.com.votacao.controller;

import br.com.votacao.dto.request.VotoRequest;
import br.com.votacao.model.OpcaoVoto;
import br.com.votacao.model.Voto;
import br.com.votacao.service.VotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VotoController.class)
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoService votoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarVotoComSucesso()throws Exception{

        Long sessaoId = 1L;
        String CPF = "123456789";
        VotoRequest request = new VotoRequest(sessaoId,CPF,OpcaoVoto.SIM);

        mockMvc.perform(post("/api/v1/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isCreated());

        Mockito.verify(votoService).registrarVoto(Mockito.any());
    }
}
