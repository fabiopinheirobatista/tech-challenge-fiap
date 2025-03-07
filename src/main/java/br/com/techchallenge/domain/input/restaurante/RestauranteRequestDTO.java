package br.com.techchallenge.domain.input.restaurante;

import br.com.techchallenge.domain.useCase.endereco.Endereco;

public record RestauranteRequestDTO(
        String nome,
        Endereco endereco,
        String tipoCozinha,
        Long idDonoRestaurante
) {
    public String getNome() {
        return nome;
    }

    public Long getIdDonoRestaurante() {
        return idDonoRestaurante;
    }
}
