package br.com.techchallenge.domain.output.restaurante;

import br.com.techchallenge.domain.entity.Endereco;

public record RestauranteListarTodosResponseDTO(
        Long id,
        String nome,
        Endereco endereco,
        String tipoCozinha
) {
}
