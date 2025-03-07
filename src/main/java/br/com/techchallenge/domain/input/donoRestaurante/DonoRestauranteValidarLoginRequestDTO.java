package br.com.techchallenge.domain.input.donoRestaurante;

public record DonoRestauranteValidarLoginRequestDTO(
        Long id,
        String login,
        String senha
) {
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
