package br.com.techchallenge.adapters.dto.Restaurante;

import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.domain.endereco.Endereco;

public record RestauranteResponseDTO(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        DonoRestauranteSimplesResponseDto donoRestaurante
) {}
