package br.com.votacao.service;

import br.com.votacao.dto.request.AbrirSessaoRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.repository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService {
    @Autowired
    private SessaoVotacaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    public SessaoVotacao abrirSessao(AbrirSessaoRequest request){
        Pauta pauta = pautaService.buscarPorId(request.getPautaId());

        if(sessaoRepository.findByPautaId(pauta.getId()).isPresent()){
            throw new IllegalStateException("Sessão já aberta para está pauta");
        }

        LocalDateTime agora = LocalDateTime.now();
        Long duracao = request.getDuracaoEmMinutos() != null ? request.getDuracaoEmMinutos() : 1L;

        SessaoVotacao sessao = SessaoVotacao.builder()
                                            .pauta(pauta)
                                            .dataAbertura(agora)
                                            .dataFechamento(agora.plusMinutes(duracao))
                                            .build();
        
        return sessaoRepository.save(sessao);
    }

    public SessaoVotacao buscarSessaoAtiva(Long sessaoId){
        SessaoVotacao sessao = sessaoRepository.findById(sessaoId)
                                               .orElseThrow(() -> new IllegalArgumentException("Sessão Não Encontrada: " + sessaoId));

        if(sessao.getDataFechamento().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("Sessão de Votação Encerrada");
        }

        return sessao;
    }
}