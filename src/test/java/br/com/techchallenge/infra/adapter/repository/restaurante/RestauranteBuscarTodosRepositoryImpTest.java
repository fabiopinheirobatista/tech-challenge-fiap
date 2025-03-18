package br.com.techchallenge.infra.adapter.repository.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RestauranteBuscarTodosRepositoryImpTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteDTOConverter converter;

    @InjectMocks
    private RestauranteBuscarTodosRepositoryImp buscarTodosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDeRestaurantes_QuandoExistiremRestaurantes() {
        RestauranteEntity restauranteEntity1 = new RestauranteEntity(1L, "Restaurante A", null, "Brasileira", null);
        RestauranteEntity restauranteEntity2 = new RestauranteEntity(2L, "Restaurante B", null, "Italiana", null);
        Restaurante restaurante1 = new Restaurante(1L, "Restaurante A", null, "Brasileira", null);
        Restaurante restaurante2 = new Restaurante(2L, "Restaurante B", null, "Italiana", null);

        when(repository.findAll()).thenReturn(Arrays.asList(restauranteEntity1, restauranteEntity2));
        when(converter.restauranteEntityToRestaurante(restauranteEntity1)).thenReturn(restaurante1);
        when(converter.restauranteEntityToRestaurante(restauranteEntity2)).thenReturn(restaurante2);

        List<Restaurante> resultado = buscarTodosRepository.buscarTodos();

        assertEquals(2, resultado.size());
        assertEquals(restaurante1, resultado.get(0));
        assertEquals(restaurante2, resultado.get(1));
    }

    @Test
    void deveRetornarListaVazia_QuandoNaoExistiremRestaurantes() {
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<Restaurante> resultado = buscarTodosRepository.buscarTodos();

        assertEquals(0, resultado.size());
    }
}