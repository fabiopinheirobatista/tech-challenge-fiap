package br.com.techchallenge.domain.output.donoRestaurante;

public record DonoRestauranteListarIdResponseDTO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login
) {
}

