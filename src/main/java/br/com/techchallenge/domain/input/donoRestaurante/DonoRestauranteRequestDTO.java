package br.com.techchallenge.domain.input.donoRestaurante;

import br.com.techchallenge.domain.useCase.endereco.Endereco;

public record DonoRestauranteRequestDTO(
    Long id,
    String nome,
    Endereco endereco,
    String email,
    String login
) {
    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
}
