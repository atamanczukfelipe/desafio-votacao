package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique =  true)
    private String cpfAssociado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OpcaoVoto voto;

    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    private SessaoVotacao sessao;
    
}
