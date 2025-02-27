package br.com.techchallenge.infra.service.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.restaurante.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    private BuscarRestaurantePorIdService buscarRestaurantePorIdService;

    @Test
    void deveRetornarRestauranteQuandoBuscarPorId() {

        Restaurante restaurante = new Restaurante(1L, "Restaurante A", null, "Italiana", null);
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "Restaurante A", null, "Italiana", null);


        when(repository.findById(1L)).thenReturn(Optional.of(restauranteEntity));
        when(mapper.toRestaurante(any())).thenReturn(restaurante);

        Optional<Restaurante> result = buscarRestaurantePorIdService.buscarPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Restaurante A", result.get().getNome());
        assertEquals("Italiana", result.get().getTipoCozinha());

        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toRestaurante(restauranteEntity);
    }


}