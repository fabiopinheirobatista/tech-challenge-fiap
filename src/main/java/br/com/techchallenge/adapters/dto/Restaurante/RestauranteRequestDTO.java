package br.com.techchallenge.adapters.dto.Restaurante;

import br.com.techchallenge.domain.Endereco;

public record RestauranteRequestDTO(
        String nome,
        Endereco endereco,
        String tipoCozinha,
        Long donoRestaurante
) {}
