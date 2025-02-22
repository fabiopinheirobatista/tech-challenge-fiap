package br.com.techchallenge.adapters.response;

public record DonoRestauranteResponse(
        String message,
        String nome,
        String endereco,
        String email,
        String login
) {}
