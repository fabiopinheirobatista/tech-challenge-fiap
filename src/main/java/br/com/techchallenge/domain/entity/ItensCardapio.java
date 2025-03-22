package br.com.techchallenge.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItensCardapio {
    private Long id;
    private String nome;
    private String descricao;
    private double preco;
    private String disponibilidade;
    private String fotoPrato;
    private Long idRestaurante;

    public ItensCardapio(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
}