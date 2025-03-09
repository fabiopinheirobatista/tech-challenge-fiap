package br.com.techchallenge.domain.dto.clienteRestaurante;

public record ClienteRestauranteResponseDto(
        Long id,
        String nome,
        String email,
        String login
) {
}
