package br.com.techchallenge.adapters.dto.donoRestaurante;

public record DonoRestauranteResponseRestauranteDTO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login
) {
}
