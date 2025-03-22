package br.com.techchallenge.infra.adapter.repository.itensCardapio;

import br.com.techchallenge.domain.entity.ItensCardapio;
import br.com.techchallenge.infra.converter.itensCardapio.ItensCardapioDTOConverter;
import br.com.techchallenge.infra.entity.ItensCardapioEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import br.com.techchallenge.infra.repository.ItensCardapioRepository;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensCardapioCadastrarRepositoryImpTest {

    @Mock
    private ItensCardapioRepository repositoryItensCardapio;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private ItensCardapioDTOConverter itensCardapioDTOConverter;

    @InjectMocks
    private ItensCardapioCadastrarRepositoryImp itensCardapioCadastrarRepository;

    @Test
    @DisplayName("Deve salvar um item do cardápio com sucesso")
    void deveSalvarUmItemDoCardapioComSucesso() {
        Long id = 1L;
        String nome = "Feijoada Completa";
        String descricao = "Feijoada tradicional com acompanhamentos";
        BigDecimal valor = new BigDecimal("45.90");
        String categoria = "PRATO_PRINCIPAL";

        ItensCardapio itemDomain = new ItensCardapio();
        itemDomain.setNome(nome);
        itemDomain.setDescricao(descricao);

        ItensCardapioEntity itemEntity = new ItensCardapioEntity();
        itemEntity.setNome(nome);
        itemEntity.setDescricao(descricao);

        ItensCardapioEntity itemSalvo = new ItensCardapioEntity();
        itemSalvo.setId(id);
        itemSalvo.setNome(nome);
        itemSalvo.setDescricao(descricao);

        ItensCardapio itemDomainSalvo = new ItensCardapio();
        itemDomainSalvo.setId(id);
        itemDomainSalvo.setNome(nome);
        itemDomainSalvo.setDescricao(descricao);

        when(itensCardapioDTOConverter.domainToEntity(itemDomain)).thenReturn(itemEntity);
        when(repositoryItensCardapio.save(itemEntity)).thenReturn(itemSalvo);
        when(itensCardapioDTOConverter.entityToDomain(itemSalvo)).thenReturn(itemDomainSalvo);

        ItensCardapio resultado = itensCardapioCadastrarRepository.salvar(itemDomain);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(descricao, resultado.getDescricao());

        verify(itensCardapioDTOConverter, times(1)).domainToEntity(itemDomain);
        verify(repositoryItensCardapio, times(1)).save(itemEntity);
        verify(itensCardapioDTOConverter, times(1)).entityToDomain(itemSalvo);
    }

    @Test
    @DisplayName("Deve buscar restaurante por ID com sucesso")
    void deveBuscarRestaurantePorIdComSucesso() {
        Long idRestaurante = 1L;
        String nomeRestaurante = "Restaurante Sabor Brasileiro";

        RestauranteEntity restauranteEntity = new RestauranteEntity();
        restauranteEntity.setId(idRestaurante);
        restauranteEntity.setNome(nomeRestaurante);

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.of(restauranteEntity));

        Optional<RestauranteEntity> resultado = itensCardapioCadastrarRepository.buscarPorId(idRestaurante);

        assertTrue(resultado.isPresent());
        assertEquals(idRestaurante, resultado.get().getId());
        assertEquals(nomeRestaurante, resultado.get().getNome());

        verify(restauranteRepository, times(1)).findById(idRestaurante);
    }
}
