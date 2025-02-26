package br.com.techchallenge.adapters.controller.dto;

public record DonoRestauranteResponseDTO(
        String nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
