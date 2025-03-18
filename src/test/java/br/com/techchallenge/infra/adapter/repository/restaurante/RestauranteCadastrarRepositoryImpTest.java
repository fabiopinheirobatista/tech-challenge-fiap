package br.com.techchallenge.infra.adapter.repository.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestauranteCadastrarRepositoryImpTest {

    @Mock
    private RestauranteRepository repositoryRestaurante;

    @Mock
    private DonoRestauranteRepository repositoryDonoRestaurante;

    @Mock
    private RestauranteDTOConverter converterRestaurante;

    @Mock
    private DonoRestauranteDTOConverter converterDonoRestaurante;

    @InjectMocks
    private RestauranteCadastrarRepositoryImp cadastrarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarRestaurante_QuandoDadosValidos() {
        Restaurante restaurante = new Restaurante(1L, "Restaurante A", null, "Brasileira", null);
        RestauranteEntity restauranteEntity = new RestauranteEntity(1L, "Restaurante A", null, "Brasileira", null);

        when(converterRestaurante.restauranteParaEntity(any(Restaurante.class))).thenReturn(restauranteEntity);
        when(repositoryRestaurante.save(any(RestauranteEntity.class))).thenReturn(restauranteEntity);
        when(converterRestaurante.restauranteEntityToRestaurante(any(RestauranteEntity.class))).thenReturn(restaurante);

        Restaurante resultado = cadastrarRepository.salvar(restaurante);

        assertEquals("Restaurante A", resultado.getNome());
        assertEquals("Brasileira", resultado.getTipoCozinha());
    }

    @Test
    void deveRetornarTrue_QuandoNomeExistir() {
        String nome = "Restaurante A";

        when(repositoryRestaurante.existsByNome(nome)).thenReturn(true);

        boolean resultado = cadastrarRepository.buscarPorNome(nome);

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalse_QuandoNomeNaoExistir() {
        String nome = "Restaurante B";

        when(repositoryRestaurante.existsByNome(nome)).thenReturn(false);

        boolean resultado = cadastrarRepository.buscarPorNome(nome);

        assertFalse(resultado);
    }

    @Test
    void deveRetornarDonoRestaurante_QuandoIdExistir() {
        Long id = 1L;
        DonoRestauranteEntity donoRestauranteEntity = new DonoRestauranteEntity();
        donoRestauranteEntity.setId(id);
        DonoRestaurante donoRestaurante = new DonoRestaurante();

        when(repositoryDonoRestaurante.findById(id)).thenReturn(Optional.of(donoRestauranteEntity));
        when(converterDonoRestaurante.entityParaDonoRestaurante(donoRestauranteEntity)).thenReturn(donoRestaurante);

        Optional<DonoRestaurante> resultado = cadastrarRepository.buscarPorIdDonoRestaurante(id);

        assertTrue(resultado.isPresent());
        assertEquals(donoRestaurante, resultado.get());
    }

    @Test
    void deveRetornarVazio_QuandoIdNaoExistir() {
        Long id = 1L;

        when(repositoryDonoRestaurante.findById(id)).thenReturn(Optional.empty());

        Optional<DonoRestaurante> resultado = cadastrarRepository.buscarPorIdDonoRestaurante(id);

        assertTrue(resultado.isEmpty());
    }
}