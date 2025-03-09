package br.com.techchallenge.domain.dto.clienteRestaurante;

public record ClienteRestauranteRequestDto(
    String nome,
    String email,
    String login,
    String senha
) {
}
