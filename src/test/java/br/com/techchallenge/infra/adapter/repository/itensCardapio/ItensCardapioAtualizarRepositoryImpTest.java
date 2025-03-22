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

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensCardapioAtualizarRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter itensCardapioDTOConverter;

    @InjectMocks
    private ItensCardapioAtualizarRepositoryImp itensCardapioAtualizarRepository;

    @Test
    @DisplayName("Deve atualizar item do cardápio com sucesso")
    void deveAtualizarItemDoCardapioComSucesso() {
        Long id = 1L;
        String nome = "Prato Principal Atualizado";
        String descricao = "Descrição atualizada do prato principal";
        BigDecimal valor = new BigDecimal("59.90");
        String categoria = "PRATO_PRINCIPAL";

        ItensCardapio itemDomain = new ItensCardapio();
        itemDomain.setId(id);
        itemDomain.setNome(nome);
        itemDomain.setDescricao(descricao);

        ItensCardapioEntity itemEntity = new ItensCardapioEntity();
        itemEntity.setId(id);
        itemEntity.setNome(nome);
        itemEntity.setDescricao(descricao);

        when(itensCardapioDTOConverter.domainToEntity(itemDomain)).thenReturn(itemEntity);
        when(repository.save(itemEntity)).thenReturn(itemEntity);
        when(itensCardapioDTOConverter.entityToDomain(itemEntity)).thenReturn(itemDomain);

        ItensCardapio resultado = itensCardapioAtualizarRepository.update(itemDomain);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(descricao, resultado.getDescricao());

        verify(itensCardapioDTOConverter, times(1)).domainToEntity(itemDomain);
        verify(repository, times(1)).save(itemEntity);
        verify(itensCardapioDTOConverter, times(1)).entityToDomain(itemEntity);
    }

    @Test
    @DisplayName("Deve atualizar item do cardápio com novos valores")
    void deveAtualizarItemDoCardapioComNovosValores() {
        Long id = 2L;
        String nomeOriginal = "Sobremesa";
        String nomeAtualizado = "Sobremesa Especial";
        String descricaoOriginal = "Descrição da sobremesa";
        String descricaoAtualizada = "Descrição atualizada da sobremesa especial";
        BigDecimal valorOriginal = new BigDecimal("15.90");
        BigDecimal valorAtualizado = new BigDecimal("19.90");
        String categoria = "SOBREMESA";

        ItensCardapio itemDomainOriginal = new ItensCardapio();
        itemDomainOriginal.setId(id);
        itemDomainOriginal.setNome(nomeOriginal);
        itemDomainOriginal.setDescricao(descricaoOriginal);

        ItensCardapio itemDomainAtualizado = new ItensCardapio();
        itemDomainAtualizado.setId(id);
        itemDomainAtualizado.setNome(nomeAtualizado);
        itemDomainAtualizado.setDescricao(descricaoAtualizada);

        ItensCardapioEntity itemEntityOriginal = new ItensCardapioEntity();
        itemEntityOriginal.setId(id);
        itemEntityOriginal.setNome(nomeOriginal);
        itemEntityOriginal.setDescricao(descricaoOriginal);

        ItensCardapioEntity itemEntityAtualizado = new ItensCardapioEntity();
        itemEntityAtualizado.setId(id);
        itemEntityAtualizado.setNome(nomeAtualizado);
        itemEntityAtualizado.setDescricao(descricaoAtualizada);

        when(itensCardapioDTOConverter.domainToEntity(itemDomainAtualizado)).thenReturn(itemEntityAtualizado);
        when(repository.save(itemEntityAtualizado)).thenReturn(itemEntityAtualizado);
        when(itensCardapioDTOConverter.entityToDomain(itemEntityAtualizado)).thenReturn(itemDomainAtualizado);

        ItensCardapio resultado = itensCardapioAtualizarRepository.update(itemDomainAtualizado);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nomeAtualizado, resultado.getNome());
        assertEquals(descricaoAtualizada, resultado.getDescricao());

        verify(itensCardapioDTOConverter, times(1)).domainToEntity(itemDomainAtualizado);
        verify(repository, times(1)).save(itemEntityAtualizado);
        verify(itensCardapioDTOConverter, times(1)).entityToDomain(itemEntityAtualizado);
    }
}