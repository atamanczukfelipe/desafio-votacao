package br.com.votacao.repository;

import br.com.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    boolean existsByCpfAssociadoAndSessaoId(String cpfAssociado, Long sessaoId);
    List<Voto> findAllBySessaoId(Long sessaoId);    
}