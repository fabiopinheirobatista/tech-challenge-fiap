package br.com.techchallenge.infra.adapter.repository;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.gateway.clienteRestaurante.BuscarTodosOsClientesInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuscarTodosOsClientesRepositoryImpl implements BuscarTodosOsClientesInterface {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

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
