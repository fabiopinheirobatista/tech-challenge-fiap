package br.com.techchallenge.infra.adapter.repository;

import br.com.techchallenge.domain.entity.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.domain.gateway.ClienteRestauranteInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class ClienteRestauranteRepositoryImpl implements ClienteRestauranteInterface {

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

    @Override
    public List<ClienteRestaurante> buscarTodos() {
        List<ClienteRestauranteEntity> listaClientesEntity = clienteRestauranteRepository.findAll();
        List<ClienteRestaurante> listaClienteRestaurante = new ArrayList<>();

        for (ClienteRestauranteEntity clienteEntity : listaClientesEntity) {
            ClienteRestaurante clienteRestaurante = new ClienteRestaurante(
                    clienteEntity.getId(),
                    clienteEntity.getNome(),
                    clienteEntity.getEmail(),
                    clienteEntity.getLogin(),
                    clienteEntity.getSenha()
            );
            listaClienteRestaurante.add(clienteRestaurante);
        }

        return listaClienteRestaurante;
    }
}
