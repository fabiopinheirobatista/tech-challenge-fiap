package br.com.techchallenge.domain.useCase.cliente;

import br.com.techchallenge.domain.gateway.ClienteRestauranteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class DeleteClienteUseCase {

    private final ClienteRestauranteInterface clienteRestauranteInterface;

    public void execute(Long id) {
        try {
            clienteRestauranteInterface.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar cliente" + e.getMessage());
        }
    }

}
