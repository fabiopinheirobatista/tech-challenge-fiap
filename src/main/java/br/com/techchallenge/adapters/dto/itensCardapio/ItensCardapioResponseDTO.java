package br.com.techchallenge.adapters.dto.itensCardapio;

public record ItensCardapioResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        String disponibilidade,
        String fotoprato) {
}
