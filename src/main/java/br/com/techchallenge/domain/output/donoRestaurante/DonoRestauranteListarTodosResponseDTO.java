package br.com.techchallenge.domain.output.donoRestaurante;

public record DonoRestauranteListarTodosResponseDTO(
        Long id,
        String nome,
        String endereco,
        String email,
        String login
) {
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }
}

