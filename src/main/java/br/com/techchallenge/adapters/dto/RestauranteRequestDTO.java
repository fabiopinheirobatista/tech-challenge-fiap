package br.com.techchallenge.adapters.dto;

import br.com.techchallenge.domain.Endereco;

public record RestauranteRequestDTO(
        String nome,
        Endereco endereco,
        String tipoCozinha,
        Long donoRestaurante
) {}
