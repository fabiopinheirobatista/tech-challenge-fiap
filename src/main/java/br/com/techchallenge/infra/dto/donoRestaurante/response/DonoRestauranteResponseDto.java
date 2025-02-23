package br.com.techchallenge.infra.dto.donoRestaurante.response;

public record DonoRestauranteResponseDto(
        String nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
