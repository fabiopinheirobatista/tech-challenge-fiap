package br.com.techchallenge.domain.output.clienteRestaurante;

public record ClienteRestauranteResponseDto(
        Long id,
        String nome,
        String email,
        String login
) {
}
