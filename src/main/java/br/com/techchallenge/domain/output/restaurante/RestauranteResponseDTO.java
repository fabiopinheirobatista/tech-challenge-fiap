package br.com.techchallenge.domain.output.restaurante;

import br.com.techchallenge.domain.useCase.endereco.Endereco;
import br.com.techchallenge.domain.output.donoRestaurante.DonoRestauranteResponseDTO;

public record RestauranteResponseDTO(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        DonoRestauranteResponseDTO donoRestaurante
) {}
