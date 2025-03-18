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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class RestauranteBuscarPorIdRepositoryImpTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteDTOConverter converter;

    @InjectMocks
    private RestauranteBuscarPorIdRepositoryImp buscarPorIdRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarRestaurante_QuandoIdExistir() {
        Long id = 1L;
        RestauranteEntity restauranteEntity = new RestauranteEntity(id, "Restaurante A", null, "Brasileira", null);
        Restaurante restaurante = new Restaurante(id, "Restaurante A", null, "Brasileira", null);

        when(repository.findById(id)).thenReturn(Optional.of(restauranteEntity));
        when(converter.restauranteEntityToRestaurante(restauranteEntity)).thenReturn(restaurante);

        Optional<Restaurante> resultado = buscarPorIdRepository.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(restaurante, resultado.get());
    }

    @Test
    void deveRetornarVazio_QuandoIdNaoExistir() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Restaurante> resultado = buscarPorIdRepository.buscarPorId(id);

        assertTrue(resultado.isEmpty());
    }
}