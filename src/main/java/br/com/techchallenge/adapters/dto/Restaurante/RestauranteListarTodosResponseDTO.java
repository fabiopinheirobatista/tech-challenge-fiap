package br.com.techchallenge.adapters.dto.Restaurante;

public record RestauranteListarTodosResponseDTO(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha
) {
}
