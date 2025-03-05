package br.com.techchallenge.adapters.dto.itensCardapio;

public record ItensCardapioListarTodosResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double preco,
        String disponibilidade,
        String fotoprato) {

}
