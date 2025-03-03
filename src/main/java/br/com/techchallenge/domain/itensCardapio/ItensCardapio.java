package br.com.techchallenge.domain.itensCardapio;

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
    private Double preco;
    private String disponibilidade;
    private String fotoPrato;
}
