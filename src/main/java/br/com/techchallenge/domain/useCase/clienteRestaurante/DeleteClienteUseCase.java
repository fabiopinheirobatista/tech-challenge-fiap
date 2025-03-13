package br.com.techchallenge.domain.useCase.clienteRestaurante;

import br.com.techchallenge.domain.gateway.clienteRestaurante.DeleteClienteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Getter
@Component
@RequiredArgsConstructor
public class DeleteClienteUseCase {

    private final DeleteClienteInterface clienteRestauranteInterface;

    public void execute(Long id) {
        try {
            clienteRestauranteInterface.delete(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar cliente" + e.getMessage());
        }
    }

}
