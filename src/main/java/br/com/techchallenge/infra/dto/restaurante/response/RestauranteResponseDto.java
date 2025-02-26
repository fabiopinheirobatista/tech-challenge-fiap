package br.com.techchallenge.infra.dto.restaurante.response;

import br.com.techchallenge.domain.Endereco;
import br.com.techchallenge.infra.dto.donoRestaurante.response.DonoRestauranteSimplesResponseDto;

public record RestauranteResponseDto(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        DonoRestauranteSimplesResponseDto donoRestaurante
) {}
