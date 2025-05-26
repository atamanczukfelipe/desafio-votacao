package br.com.votacao.service;

import br.com.votacao.dto.request.VotoRequest;
import br.com.votacao.dto.response.ResultadoVotacaoResponce;
import br.com.votacao.model.*;
import br.com.votacao.repository.SessaoVotacaoRepository;
import br.com.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private SessaoService sessaoService;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Transactional
    public void registrarVoto(VotoRequest request) {
        SessaoVotacao sessao = sessaoService.buscarSessaoAtiva(request.getSessaoId());

        if(votoRepository.existsByCpfAssociadoAndSessaoId(request.getCpfAssociado(), sessao.getId())){
            throw new IllegalStateException("CPF já votou nesta sessão");
        }

        OpcaoVoto votoEnum = request.getVoto();

        Voto voto = Voto.builder()
                        .cpfAssociado(request.getCpfAssociado())
                        .voto(votoEnum)
                        .sessao(sessao)
                        .build();

        votoRepository.save(voto);
    }

    public ResultadoVotacaoResponce apurarResultado(Long pautaId){
        SessaoVotacao sessao = sessaoVotacaoRepository.findByPautaId(pautaId)
                                                             .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada para a pauta: " + pautaId));

        List<Voto> votos = votoRepository.findAllBySessaoId(sessao.getId());


        long sim = votos.stream().filter(v -> v.getVoto() == OpcaoVoto.SIM).count();
        long nao = votos.stream().filter(v -> v.getVoto() == OpcaoVoto.NAO).count();

        return ResultadoVotacaoResponce.builder()
                                       .pautaId(pautaId)
                                       .titulo(sessao.getPauta().getTitulo())
                                       .totalSim(sim)   
                                       .totalNao(nao)
                                       .encerrada(sessao.getDataFechamento().isBefore(LocalDateTime.now()))
                                       .build();


    }   
}