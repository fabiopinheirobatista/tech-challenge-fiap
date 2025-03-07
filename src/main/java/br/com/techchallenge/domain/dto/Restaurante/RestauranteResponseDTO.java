package br.com.techchallenge.domain.dto.Restaurante;

import br.com.techchallenge.domain.dto.donoRestaurante.DonoRestauranteResponseDTO;
import br.com.techchallenge.domain.endereco.Endereco;

public record RestauranteResponseDTO(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha,
        DonoRestauranteResponseDTO donoRestaurante
) {}
