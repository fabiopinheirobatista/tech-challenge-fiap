package br.com.techchallenge.domain.input.clienteRestaurante;

public record ClienteRestauranteRequestDto(
    String nome,
    String email,
    String login,
    String senha
) {
}
