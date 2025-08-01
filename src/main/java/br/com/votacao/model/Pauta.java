package br.com.votacao.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;
    
    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL)
    private SessaoVotacao sessao;
    
}
