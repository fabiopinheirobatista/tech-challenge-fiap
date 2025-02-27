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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarRestaurantesServiceTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteMapper mapper;

    @InjectMocks
    private BuscarRestaurantesService buscarRestaurantesService;


    @Test
    void deveRetornarListaDeRestaurantesQuandoBuscarTodos() {

        List<RestauranteEntity> entities = List.of(
                new RestauranteEntity(1L, "Restaurante A", null, "Italiana", null),
                new RestauranteEntity(2L, "Restaurante B", null, "Japonesa", null)
        );

        List<Restaurante> expectedRestaurantes = List.of(
                new Restaurante(1L, "Restaurante A", null, "Italiana", null),
                new Restaurante(2L, "Restaurante B", null, "Japonesa", null)
        );

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toRestauranteList(entities)).thenReturn(expectedRestaurantes);

        List<Restaurante> result = buscarRestaurantesService.buscarTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Restaurante A", result.get(0).getNome());
        assertEquals("Restaurante B", result.get(1).getNome());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toRestauranteList(entities);
    }


}