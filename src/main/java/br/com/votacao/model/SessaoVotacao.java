package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SessaoVotacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private Pauta pauta;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    @Column(nullable = false)
    private LocalDateTime dataFechamento;

    @OneToMany(mappedBy = "sessao", cascade = CascadeType.ALL)
    private List<Voto> votos;
}
