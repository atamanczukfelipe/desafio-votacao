package br.com.votacao.service;

import br.com.votacao.dto.request.AbrirSessaoRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.SessaoVotacao;
import br.com.votacao.repository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class SessaoService {
    @Autowired
    private SessaoVotacaoRepository sessaoRepository;

    @Autowired
    private PautaService pautaService;

    @Value("${votacao.sessao.duracao-default-minutos}")
    private Long duracaoDefaultMinutos;

    public SessaoVotacao abrirSessao(AbrirSessaoRequest request){
        Pauta pauta = pautaService.buscarPorId(request.getPautaId());

        if(sessaoRepository.findByPautaId(pauta.getId()).isPresent()){
            throw new IllegalStateException("Sessão já aberta para está pauta");
        }

        LocalDateTime agora = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Long duracao = request.getDuracaoEmMinutos() != null ? request.getDuracaoEmMinutos() : duracaoDefaultMinutos;

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