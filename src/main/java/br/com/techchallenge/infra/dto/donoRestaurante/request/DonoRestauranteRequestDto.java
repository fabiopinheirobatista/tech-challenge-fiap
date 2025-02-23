package br.com.techchallenge.infra.dto.donoRestaurante.request;

public record DonoRestauranteRequestDto(
        String nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
