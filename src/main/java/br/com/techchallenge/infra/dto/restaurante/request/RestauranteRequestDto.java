package br.com.techchallenge.infra.dto.restaurante.request;

import br.com.techchallenge.domain.Endereco;

public record RestauranteRequestDto(
        String nome,
        Endereco endereco,
        String tipoCozinha
) {}
