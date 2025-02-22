package br.com.techchallenge.infra.dto.restaurante.response;

public record RestauranteResponseDto(
        String nome,
        String endereco,
        String tipoCozinha
) {}
