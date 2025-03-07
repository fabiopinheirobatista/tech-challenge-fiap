package br.com.techchallenge.domain.dto.donoRestaurante;

public record DonoRestauranteListarTodosResponseDTO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login
) {
}

