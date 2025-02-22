package br.com.techchallenge.adapters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record DonoRestauranteRequest(
        String nome,
        String endereco,
        String email,
        String login,
        String senha
) {}
