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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestauranteAtualizarRepositoryImpTest {

    @Mock
    private RestauranteRepository repository;

    @Mock
    private RestauranteDTOConverter converter;

    @InjectMocks
    private RestauranteAtualizarRepositoryImp atualizarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarRestaurante_QuandoDadosValidos() {
        Restaurante restaurante = new Restaurante( 1L, "Restaurante A", null, "Brasileira", null);
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "Restaurante A", null, "Brasileira", null);

        when(converter.restauranteParaEntity(any(Restaurante.class))).thenReturn(restauranteEntity);
        when(repository.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);
        when(converter.restauranteEntityToRestaurante(any(RestauranteEntity.class))).thenReturn(restaurante);

        Restaurante resultado = atualizarRepository.update(restaurante);

        assertEquals("Restaurante A", resultado.getNome());
        assertEquals("Brasileira", resultado.getTipoCozinha());
    }
}