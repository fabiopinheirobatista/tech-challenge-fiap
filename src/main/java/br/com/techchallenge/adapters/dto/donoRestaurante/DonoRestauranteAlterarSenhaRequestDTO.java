package br.com.techchallenge.adapters.dto.donoRestaurante;

public record DonoRestauranteAlterarSenhaRequestDTO(
        Long id,
        String email,
        String senhaAtual,
        String novaSenha
) {
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }
}
