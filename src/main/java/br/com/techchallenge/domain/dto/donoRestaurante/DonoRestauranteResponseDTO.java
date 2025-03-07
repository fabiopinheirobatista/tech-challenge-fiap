package br.com.techchallenge.domain.dto.donoRestaurante;

public record DonoRestauranteResponseDTO(
        Long nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
