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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensCardapioBuscarPorIdRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter converter;

    @InjectMocks
    private ItensCardapioBuscarPorIdRepositoryImp buscarPorIdRepository;

    @Test
    @DisplayName("Deve retornar item do cardápio quando encontrado por ID")
    void deveRetornarItemDoCardapioQuandoEncontradoPorId() {
        Long id = 1L;
        String nome = "Prato Principal";
        String descricao = "Descrição do prato principal";

        ItensCardapioEntity itemEntity = new ItensCardapioEntity();
        itemEntity.setId(id);
        itemEntity.setNome(nome);
        itemEntity.setDescricao(descricao);

        ItensCardapio itemDomain = new ItensCardapio();
        itemDomain.setId(id);
        itemDomain.setNome(nome);
        itemDomain.setDescricao(descricao);

        when(repository.findById(id)).thenReturn(Optional.of(itemEntity));
        when(converter.entityToDomain(itemEntity)).thenReturn(itemDomain);

        Optional<ItensCardapio> resultado = buscarPorIdRepository.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        assertEquals(nome, resultado.get().getNome());
        assertEquals(descricao, resultado.get().getDescricao());

        verify(repository, times(1)).findById(id);
        verify(converter, times(1)).entityToDomain(itemEntity);
    }

    @Test
    @DisplayName("Deve retornar Optional vazio quando item do cardápio não for encontrado por ID")
    void deveRetornarOptionalVazioQuandoItemDoCardapioNaoForEncontradoPorId() {
        Long id = 99L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<ItensCardapio> resultado = buscarPorIdRepository.buscarPorId(id);

        assertFalse(resultado.isPresent());
        assertTrue(resultado.isEmpty());

        verify(repository, times(1)).findById(id);
        verify(converter, never()).entityToDomain(any());
    }
}