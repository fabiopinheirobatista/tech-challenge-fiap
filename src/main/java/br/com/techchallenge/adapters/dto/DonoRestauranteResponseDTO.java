package br.com.techchallenge.adapters.dto;

public record DonoRestauranteResponseDTO(
        String nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
