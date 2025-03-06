package br.com.techchallenge.adapters.dto.Restaurante;

import br.com.techchallenge.domain.endereco.Endereco;

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
