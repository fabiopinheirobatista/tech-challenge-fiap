package br.com.techchallenge.domain.output.donoRestaurante;

public record DonoRestauranteResponseRestauranteDTO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login
) {
}
