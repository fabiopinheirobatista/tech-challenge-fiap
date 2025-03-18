package br.com.techchallenge.infra.adapter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ItensCardapioBuscarTodosRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter converter;

    @InjectMocks
    private ItensCardapioBuscarTodosRepositoryImp buscarTodosRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarListaDeItensCardapio_QuandoExistiremItens() {
        ItensCardapioEntity itemEntity1 = new ItensCardapioEntity(1L, "Item 1", "Descrição 1", 10.0, "Disponível", "URL 1", null);
        ItensCardapioEntity itemEntity2 = new ItensCardapioEntity(2L, "Item 2", "Descrição 2", 20.0, "Disponível", "URL 2", null);
        ItensCardapio item1 = new ItensCardapio(1L, "Item 1", "Descrição 1", 10.0, "Disponível", "URL 1", 1L);
        ItensCardapio item2 = new ItensCardapio(2L, "Item 2", "Descrição 2", 20.0, "Disponível", "URL 2", 2L);

        when(repository.findAll()).thenReturn(Arrays.asList(itemEntity1, itemEntity2));
        when(converter.entityToDomain(itemEntity1)).thenReturn(item1);
        when(converter.entityToDomain(itemEntity2)).thenReturn(item2);

        List<ItensCardapio> resultado = buscarTodosRepository.buscarTodos();

        assertEquals(2, resultado.size());
        assertEquals(item1, resultado.get(0));
        assertEquals(item2, resultado.get(1));
    }

    @Test
    void deveRetornarListaVazia_QuandoNaoExistiremItens() {
        when(repository.findAll()).thenReturn(Arrays.asList());

        List<ItensCardapio> resultado = buscarTodosRepository.buscarTodos();

        assertEquals(0, resultado.size());
    }
}