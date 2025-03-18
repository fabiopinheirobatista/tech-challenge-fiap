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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ItensCardapioBuscarPorIdRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter converter;

    @InjectMocks
    private ItensCardapioBuscarPorIdRepositoryImp buscarPorIdRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarItemCardapio_QuandoIdExistir() {
        Long id = 1L;
        ItensCardapioEntity itemCardapioEntity = new ItensCardapioEntity(id, "Item", "Descrição", 10.0, "Disponível", "URL", null);
        ItensCardapio itemCardapio = new ItensCardapio(id, "Item", "Descrição", 10.0, "Disponível", "URL", 1L);

        when(repository.findById(id)).thenReturn(Optional.of(itemCardapioEntity));
        when(converter.entityToDomain(itemCardapioEntity)).thenReturn(itemCardapio);

        Optional<ItensCardapio> resultado = buscarPorIdRepository.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(itemCardapio, resultado.get());
    }

    @Test
    void deveRetornarVazio_QuandoIdNaoExistir() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<ItensCardapio> resultado = buscarPorIdRepository.buscarPorId(id);

        assertTrue(resultado.isEmpty());
    }
}