package br.com.techchallenge.domain.input.donoRestaurante;

import br.com.techchallenge.domain.useCase.endereco.Endereco;

public record DonoRestauranteCadastrarRequestDTO (
        Long id,
        String nome,
        Endereco endereco,
        String email,
        String login,
        String senha
){
    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public Object getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }
}
