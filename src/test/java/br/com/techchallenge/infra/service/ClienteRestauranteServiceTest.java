package br.com.techchallenge.infra.service;

import br.com.techchallenge.domain.useCase.clienteRestaurante.ClienteRestaurante;
import br.com.techchallenge.helper.ClienteRestauranteHelper;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteServiceTest {

    @Mock
    private ClienteRestauranteRepository repository;

    @InjectMocks
    private ClienteRestauranteService service;

    private ClienteRestauranteEntity clienteRestaurante;

    @BeforeEach
    void setUp() {
        ClienteRestaurante clienteRestauranteDomain = ClienteRestauranteHelper.clienteRestaurante();
        clienteRestaurante = new ClienteRestauranteEntity();
        clienteRestaurante.setId(clienteRestauranteDomain.getId());
        clienteRestaurante.setNome(clienteRestauranteDomain.getNome());
        clienteRestaurante.setEmail(clienteRestauranteDomain.getEmail());
        clienteRestaurante.setLogin(clienteRestauranteDomain.getLogin());
        clienteRestaurante.setSenha(clienteRestauranteDomain.getSenha());
    }


    @Test
    void salvar() {

        Mockito.when(repository.save(any(ClienteRestauranteEntity.class))).thenReturn(clienteRestaurante);

        ClienteRestauranteEntity result = service.salvar(clienteRestaurante);

        assertEquals(clienteRestaurante, result);
        Mockito.verify(repository, Mockito.times(1)).save(clienteRestaurante);
    }

    @Test
    void buscarPorId() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(clienteRestaurante));

        ClienteRestauranteEntity result = service.buscarPorId(1L);

        assertEquals(clienteRestaurante, result);
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    void buscarPorId_NotFound() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(1L));
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    void buscarTodos() {
        List<ClienteRestauranteEntity> clientes = Collections.singletonList(clienteRestaurante);
        Mockito.when(repository.findAll()).thenReturn(clientes);

        List<ClienteRestauranteEntity> result = service.buscarTodos();

        assertEquals(clientes, result);
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    void deletar() {
        Mockito.when(repository.existsById(anyLong())).thenReturn(true);

        service.deletar(1L);

        Mockito.verify(repository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void deletar_NotFound() {
        Mockito.when(repository.existsById(anyLong())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.deletar(1L));
        Mockito.verify(repository, Mockito.times(1)).existsById(1L);
    }
}