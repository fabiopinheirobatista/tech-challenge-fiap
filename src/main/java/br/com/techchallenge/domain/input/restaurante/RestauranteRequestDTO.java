package br.com.techchallenge.domain.input.restaurante;

import br.com.techchallenge.domain.entity.Endereco;

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
