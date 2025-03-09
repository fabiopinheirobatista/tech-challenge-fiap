package br.com.techchallenge.infra.adapter.repository.clienteRestaurante;

import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.DeleteClienteInterface;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteClienteRepositoryImpl implements DeleteClienteInterface {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

    @Override
    public Boolean delete(Long id) throws ClienteNaoEncontradoException {
        try {
            clienteRestauranteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new ClienteNaoEncontradoException("Cliente de Restaurante não encontrado");
        }

    }
}
