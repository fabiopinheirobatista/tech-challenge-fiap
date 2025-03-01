package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.helper.RestauranteHelper;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarRestaurantePorIdServiceTest {


    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private BuscarRestaurantePorIdService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdRestauranteEncontrado() {
        Long id = 1L;
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        Restaurante restaurante = RestauranteHelper.restaurante();

        when(repository.findById(id)).thenReturn(Optional.of(restauranteEntity));
        when(mapper.toRestaurante(restauranteEntity)).thenReturn(restaurante);

        Optional<Restaurante> resultado = service.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(restaurante, resultado.get());

        verify(repository).findById(id);
        verify(mapper).toRestaurante(restauranteEntity);
    }

    @Test
    void testBuscarPorIdRestauranteNaoEncontrado() {
        Long id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Restaurante> resultado = service.buscarPorId(id);

        assertFalse(resultado.isPresent());

        verify(repository).findById(id);
        verify(mapper, never()).toRestaurante(any());
    }
}