package br.com.techchallenge.domain.input.donoRestaurante;

import br.com.techchallenge.domain.entity.Endereco;

public record DonoRestauranteRequestDTO(
        Long id,
        String nome,
        Endereco endereco,
        String email,
        String login
) {
    public DonoRestauranteRequestDTO(Long id, String nome, Endereco endereco, String email, String login) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
}
