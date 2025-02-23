package br.com.techchallenge.infra.dto.donoRestaurante.request;

public record DonoRestauranteUpdateRequestDto(
        String nome,
        String endereco,
        String email,
        String login
) {
}
