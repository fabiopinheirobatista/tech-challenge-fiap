package br.com.techchallenge.infra.adapter.repository;

import br.com.techchallenge.domain.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.domain.gateway.BuscarClienteInterface;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class BuscarClienteRepository implements BuscarClienteInterface {

    private final ClienteRestauranteRepository clienteRestauranteRepository;

    @Override
    public ClienteRestaurante buscarPorId(Long id) {
        ClienteRestauranteEntity clienteEntity = clienteRestauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente de Restaurante não encontrado"));

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
