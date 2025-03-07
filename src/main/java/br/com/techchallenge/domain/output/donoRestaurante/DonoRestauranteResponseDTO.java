package br.com.techchallenge.domain.output.donoRestaurante;

public record DonoRestauranteResponseDTO(
        Long nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
