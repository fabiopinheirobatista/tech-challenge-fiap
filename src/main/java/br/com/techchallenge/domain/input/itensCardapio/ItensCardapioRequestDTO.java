package br.com.techchallenge.domain.input.itensCardapio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItensCardapioRequestDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        String nome,
        @NotBlank(message = "A descrição não pode estar em branco")
        String descricao,
        @NotNull(message = "O preço não pode ser nulo")
        @Positive(message = "O preço deve ser um valor positivo")
        double preco,
        @NotBlank(message = "A disponibilidade não pode estar em branco")
        String disponibilidade,
        @NotBlank(message = "A URL da foto não pode estar em branco")
        String fotoPrato,
        @NotNull(message = "O ID do restaurante não pode ser nulo")
        Long idRestaurante
) {}