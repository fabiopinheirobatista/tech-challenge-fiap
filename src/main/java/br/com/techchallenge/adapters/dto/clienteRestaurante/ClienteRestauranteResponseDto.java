package br.com.techchallenge.adapters.dto.clienteRestaurante;

public record ClienteRestauranteResponseDto(
        Long id,
        String nome,
        String email,
        String login
) {
}
