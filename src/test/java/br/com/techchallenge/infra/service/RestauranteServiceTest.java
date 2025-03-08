package br.com.techchallenge.infra.service;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.helper.RestauranteHelper;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
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
import static org.mockito.Mockito.times;

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

    @BeforeEach
    void setUp() {
        Restaurante restauranteDomain = RestauranteHelper.restaurante();
        restaurante = new RestauranteEntity();
        restaurante.setId(restauranteDomain.getId());
        restaurante.setNome(restauranteDomain.getNome());
        restaurante.setTipoCozinha(restauranteDomain.getTipoCozinha());
    }

    @Test
    void buscarPorId() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(restaurante));

        Optional<RestauranteEntity> result = service.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals(restaurante, result.get());
        Mockito.verify(repository, Mockito.times(1)).findById(1L);
    }

    @Test
    void buscarTodos() {
        List<RestauranteEntity> restaurantes = Collections.singletonList(restaurante);
        Mockito.when(repository.findAll()).thenReturn(restaurantes);

        List<RestauranteEntity> result = service.buscarTodos();

        assertEquals(restaurantes, result);
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    void deletar() {
        service.deletar(1L);

        Mockito.verify(repository, times(1)).deleteById(1L);
    }
}