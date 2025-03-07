package br.com.techchallenge.domain.dto.donoRestaurante;

public record DonoRestauranteListarIdResponseDTO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login
) {
}

