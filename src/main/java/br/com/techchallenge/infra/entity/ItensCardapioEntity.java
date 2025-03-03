package br.com.techchallenge.infra.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "itens_cardapio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensCardapioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private double preco;

    @Column(nullable = false)
    private String disponibilidade;

    @Column(nullable = false)
    private String fotoPrato;

}
