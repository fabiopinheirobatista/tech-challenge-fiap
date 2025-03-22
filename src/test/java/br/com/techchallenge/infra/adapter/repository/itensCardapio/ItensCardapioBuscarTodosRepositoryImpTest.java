package br.com.techchallenge.infra.adapter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensCardapioBuscarTodosRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter converter;

    @InjectMocks
    private ItensCardapioBuscarTodosRepositoryImp buscarTodosRepository;

    @Test
    @DisplayName("Deve retornar lista com todos os itens do cardápio")
    void deveRetornarListaComTodosOsItensDoCardapio() {
        List<ItensCardapioEntity> listaEntities = new ArrayList<>();

        ItensCardapioEntity item1 = new ItensCardapioEntity();
        item1.setId(1L);
        item1.setNome("Pizza Margherita");
        item1.setDescricao("Pizza com molho de tomate, mussarela e manjericão");
        listaEntities.add(item1);

        ItensCardapioEntity item2 = new ItensCardapioEntity();
        item2.setId(2L);
        item2.setNome("Pudim de Leite");
        item2.setDescricao("Pudim de leite condensado com calda de caramelo");
        listaEntities.add(item2);

        ItensCardapio itemDomain1 = new ItensCardapio();
        itemDomain1.setId(1L);
        itemDomain1.setNome("Pizza Margherita");
        itemDomain1.setDescricao("Pizza com molho de tomate, mussarela e manjericão");

        ItensCardapio itemDomain2 = new ItensCardapio();
        itemDomain2.setId(2L);
        itemDomain2.setNome("Pudim de Leite");
        itemDomain2.setDescricao("Pudim de leite condensado com calda de caramelo");

        when(repository.findAll()).thenReturn(listaEntities);
        when(converter.entityToDomain(item1)).thenReturn(itemDomain1);
        when(converter.entityToDomain(item2)).thenReturn(itemDomain2);

        List<ItensCardapio> resultado = buscarTodosRepository.buscarTodos();

        assertEquals(2, resultado.size());
        assertEquals("Pizza Margherita", resultado.get(0).getNome());
        assertEquals("Pudim de Leite", resultado.get(1).getNome());

        verify(repository, times(1)).findAll();
        verify(converter, times(1)).entityToDomain(item1);
        verify(converter, times(1)).entityToDomain(item2);
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver itens no cardápio")
    void deveRetornarListaVaziaQuandoNaoHouverItensNoCardapio() {
        List<ItensCardapioEntity> listaVazia = new ArrayList<>();

        when(repository.findAll()).thenReturn(listaVazia);

        List<ItensCardapio> resultado = buscarTodosRepository.buscarTodos();

        assertTrue(resultado.isEmpty());
        assertEquals(0, resultado.size());

        verify(repository, times(1)).findAll();
        verify(converter, never()).entityToDomain(any(ItensCardapioEntity.class));
    }
}
