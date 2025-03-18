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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ItensCardapioAtualizarRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter converter;

    @InjectMocks
    private ItensCardapioAtualizarRepositoryImp atualizarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAtualizarItemCardapio_QuandoItemExistir() {
        ItensCardapio itemCardapio = new ItensCardapio(1L, "Item Atualizado", "Descrição Atualizada", 10.0, "Disponivel", "URL Atualizada", 1L);
        ItensCardapioEntity itemCardapioEntity = new ItensCardapioEntity(1L, "Item Atualizado", "Descrição Atualizada", 10.0, "Disponivel", "URL Atualizada", null);

        when(converter.domainToEntity(any(ItensCardapio.class))).thenReturn(itemCardapioEntity);
        when(repository.save(any(ItensCardapioEntity.class))).thenReturn(itemCardapioEntity);
        when(converter.entityToDomain(any(ItensCardapioEntity.class))).thenReturn(itemCardapio);

        ItensCardapio resultado = atualizarRepository.update(itemCardapio);

        assertEquals("Item Atualizado", resultado.getNome());
        assertEquals("Descrição Atualizada", resultado.getDescricao());
        assertEquals(10.0, resultado.getPreco());
    }
}