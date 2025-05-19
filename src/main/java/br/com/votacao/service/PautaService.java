package br.com.votacao.service;

import br.com.votacao.dto.request.CriarPautaRequest;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public Pauta criarPauta(CriarPautaRequest request){
        Pauta pauta = Pauta.builder()
                      .titulo(request.getTitulo())
                      .descricao(request.getDescricao())
                      .build();
        return pautaRepository.save(pauta);
    }

    public Pauta buscarPorId(long id){
        return pautaRepository.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException("Pauta Não Encontrada: " + id));
    }
    
}
