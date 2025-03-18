package br.com.techchallenge.infra.adapter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ItensCardapioCadastrarRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repositoryItensCardapio;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private ItensCardapioDTOConverter itensCardapioDTOConverter;

    @InjectMocks
    private ItensCardapioCadastrarRepositoryImp cadastrarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarItemCardapio_QuandoDadosValidos() {
        ItensCardapio itemCardapio = new ItensCardapio(1L, "Item", "Descrição", 10.0, "Disponível", "URL", 1L);
        ItensCardapioEntity itemCardapioEntity = new ItensCardapioEntity(1L, "Item", "Descrição", 10.0, "Disponível", "URL", null);

        when(itensCardapioDTOConverter.domainToEntity(any(ItensCardapio.class))).thenReturn(itemCardapioEntity);
        when(repositoryItensCardapio.save(any(ItensCardapioEntity.class))).thenReturn(itemCardapioEntity);
        when(itensCardapioDTOConverter.entityToDomain(any(ItensCardapioEntity.class))).thenReturn(itemCardapio);

        ItensCardapio resultado = cadastrarRepository.salvar(itemCardapio);

        assertEquals("Item", resultado.getNome());
        assertEquals("Descrição", resultado.getDescricao());
        assertEquals(10.0, resultado.getPreco());
    }

    @Test
    void deveRetornarRestaurante_QuandoIdExistir() {
        Long idRestaurante = 1L;
        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(idRestaurante);

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.of(restauranteEntity));

        Optional<RestauranteEntity> resultado = cadastrarRepository.buscarPorId(idRestaurante);

        assertTrue(resultado.isPresent());
        assertEquals(idRestaurante, resultado.get().getId());
    }

    @Test
    void deveRetornarVazio_QuandoIdNaoExistir() {
        Long idRestaurante = 1L;

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.empty());

        Optional<RestauranteEntity> resultado = cadastrarRepository.buscarPorId(idRestaurante);

        assertTrue(resultado.isEmpty());
    }
}