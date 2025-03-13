package br.com.techchallenge.infra.service;

import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestauranteServiceTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private DonoRestauranteRepository donoRestauranteRepository;

    @Mock
    private RestauranteEntity restaurante;

    @InjectMocks
    private RestauranteService service;
}