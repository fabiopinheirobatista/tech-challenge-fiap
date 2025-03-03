package br.com.techchallenge.adapters.dto.clienteRestaurante;

public record ClienteRestauranteRequestDto(
    Long id,
    String nome,
    String email,
    String login,
    String senha
) {
}
