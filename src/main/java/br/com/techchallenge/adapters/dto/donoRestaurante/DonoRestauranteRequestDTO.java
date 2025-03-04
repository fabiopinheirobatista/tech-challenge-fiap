package br.com.techchallenge.adapters.dto.donoRestaurante;

import br.com.techchallenge.domain.endereco.Endereco;

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
