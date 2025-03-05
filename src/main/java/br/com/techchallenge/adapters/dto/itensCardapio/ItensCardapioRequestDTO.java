package br.com.techchallenge.adapters.dto.itensCardapio;

public record ItensCardapioRequestDTO(
        String nome,
        String descricao,
        Double preco,
        String disponibilidade,
        String fotoprato,
        Long idRestaurante) {
}
