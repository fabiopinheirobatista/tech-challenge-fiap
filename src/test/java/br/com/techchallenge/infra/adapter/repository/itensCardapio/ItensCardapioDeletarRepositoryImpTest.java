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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItensCardapioDeletarRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repository;

    @Mock
    private ItensCardapioDTOConverter itensCardapioDTOConverter;

    @InjectMocks
    private ItensCardapioDeletarRepositoryImp itensCardapioDeletarRepository;

    @Test
    @DisplayName("Deve deletar um item do cardápio com sucesso")
    void deveDeletarUmItemDoCardapioComSucesso() {
        Long id = 1L;

        itensCardapioDeletarRepository.deletar(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve buscar item do cardápio por ID com sucesso")
    void deveBuscarItemDoCardapioPorIdComSucesso() {
        Long id = 1L;
        String nome = "Filé à Parmegiana";
        String descricao = "Filé empanado com molho de tomate e queijo gratinado";
        BigDecimal valor = new BigDecimal("52.90");
        String categoria = "PRATO_PRINCIPAL";

        ItensCardapioEntity itemEntity = new ItensCardapioEntity();
        itemEntity.setId(id);
        itemEntity.setNome(nome);
        itemEntity.setDescricao(descricao);

        ItensCardapio itemDomain = new ItensCardapio();
        itemDomain.setId(id);
        itemDomain.setNome(nome);
        itemDomain.setDescricao(descricao);

        when(repository.findById(id)).thenReturn(Optional.of(itemEntity));
        when(itensCardapioDTOConverter.entityToDomain(itemEntity)).thenReturn(itemDomain);

        Optional<ItensCardapio> resultado = itensCardapioDeletarRepository.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        assertEquals(nome, resultado.get().getNome());
        assertEquals(descricao, resultado.get().getDescricao());

        verify(repository, times(1)).findById(id);
        verify(itensCardapioDTOConverter, times(1)).entityToDomain(itemEntity);
    }
}
