package br.com.techchallenge.adapters.dto.donoRestaurante;

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
