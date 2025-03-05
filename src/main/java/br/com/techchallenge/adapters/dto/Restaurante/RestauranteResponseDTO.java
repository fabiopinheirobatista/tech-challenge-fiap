package br.com.techchallenge.adapters.dto.Restaurante;

import br.com.techchallenge.domain.endereco.Endereco;
import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteResponseDTO;

public record RestauranteResponseDTO(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        DonoRestauranteResponseDTO donoRestaurante
) {}
