package br.com.techchallenge.domain.dto.donoRestaurante;

import br.com.techchallenge.domain.endereco.Endereco;

public record DonoRestauranteRequestDTO(
    Long id,
    String nome,
    Endereco endereco,
    String email,
    String login,
    String senha
) {}
