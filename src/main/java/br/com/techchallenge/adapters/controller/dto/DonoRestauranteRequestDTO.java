package br.com.techchallenge.adapters.controller.dto;

import br.com.techchallenge.domain.Endereco;

public record DonoRestauranteRequestDTO(
    Long id,
    String nome,
    Endereco endereco,
    String email,
    String login,
    String senha
) {}
