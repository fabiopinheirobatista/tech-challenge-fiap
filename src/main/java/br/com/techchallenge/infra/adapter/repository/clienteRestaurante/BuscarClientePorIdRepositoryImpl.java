package br.com.techchallenge.infra.adapter.repository;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.clienteRestaurante.BuscarClientePorIdInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarClientePorIdRepositoryImpl implements BuscarClientePorIdInterface {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

    @Override
    public ClienteRestaurante buscarPorId(Long id) throws ClienteNaoEncontradoException {
        ClienteRestauranteEntity clienteEntity = clienteRestauranteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente de Restaurante não encontrado"));

        return new ClienteRestaurante(
                clienteEntity.getId(),
                clienteEntity.getNome(),
                clienteEntity.getEmail(),
                clienteEntity.getLogin(),
                clienteEntity.getSenha()
        );
    }
}
