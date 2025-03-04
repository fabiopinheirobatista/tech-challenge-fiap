package br.com.techchallenge.adapters.dto.clienteRestaurante;

public record ClienteRestauranteRequestDto(
    String nome,
    String email,
    String login,
    String senha
) {
}
