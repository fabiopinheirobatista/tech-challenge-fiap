package br.com.techchallenge.infra.dto.restaurante.request;

public record RestauranteRequestDto(
        String nome,
        String endereco,
        String tipoCozinha
) {}
