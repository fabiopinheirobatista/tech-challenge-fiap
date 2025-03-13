package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteServiceTest {

    @Mock
    private ClienteRestauranteRepository repository;

    @InjectMocks
    private ClienteRestauranteService service;

    private ClienteRestauranteEntity clienteRestaurante;
}