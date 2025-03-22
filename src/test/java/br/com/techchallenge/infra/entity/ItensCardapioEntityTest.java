package br.com.techchallenge.infra.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItensCardapioEntityTest {

    @Mock
    private RestauranteEntity restauranteMock;

    @Test
    @DisplayName("Deve criar uma entidade de item de cardápio com construtor vazio")
    void deveCriarEntidadeItemCardapioComConstrutorVazio() {
        ItensCardapioEntity itemCardapio = new ItensCardapioEntity();

        assertNotNull(itemCardapio);
        assertNull(itemCardapio.getId());
        assertNull(itemCardapio.getNome());
        assertNull(itemCardapio.getDescricao());
        assertEquals(0.0, itemCardapio.getPreco());
        assertNull(itemCardapio.getDisponibilidade());
        assertNull(itemCardapio.getFotoPrato());
        assertNull(itemCardapio.getRestaurante());
    }

    @Test
    @DisplayName("Deve criar uma entidade de item de cardápio com todos os parâmetros")
    void deveCriarEntidadeItemCardapioComTodosParametros() {
        Long id = 1L;
        String nome = "Prato Teste";
        String descricao = "Descrição do prato teste";
        double preco = 29.90;
        String disponibilidade = "Disponível";
        String fotoPrato = "url_da_foto.jpg";

        ItensCardapioEntity itemCardapio = new ItensCardapioEntity(id, nome, descricao,
                preco, disponibilidade, fotoPrato, restauranteMock);

        assertNotNull(itemCardapio);
        assertEquals(id, itemCardapio.getId());
        assertEquals(nome, itemCardapio.getNome());
        assertEquals(descricao, itemCardapio.getDescricao());
        assertEquals(preco, itemCardapio.getPreco());
        assertEquals(disponibilidade, itemCardapio.getDisponibilidade());
        assertEquals(fotoPrato, itemCardapio.getFotoPrato());
        assertEquals(restauranteMock, itemCardapio.getRestaurante());
    }

    @Test
    @DisplayName("Deve alterar os valores dos atributos")
    void deveAlterarValoresAtributos() {
        ItensCardapioEntity itemCardapio = new ItensCardapioEntity();

        Long id = 1L;
        String nome = "Prato Teste";
        String descricao = "Descrição do prato teste";
        double preco = 29.90;
        String disponibilidade = "Disponível";
        String fotoPrato = "url_da_foto.jpg";

        itemCardapio.setId(id);
        itemCardapio.setNome(nome);
        itemCardapio.setDescricao(descricao);
        itemCardapio.setPreco(preco);
        itemCardapio.setDisponibilidade(disponibilidade);
        itemCardapio.setFotoPrato(fotoPrato);
        itemCardapio.setRestaurante(restauranteMock);

        assertEquals(id, itemCardapio.getId());
        assertEquals(nome, itemCardapio.getNome());
        assertEquals(descricao, itemCardapio.getDescricao());
        assertEquals(preco, itemCardapio.getPreco());
        assertEquals(disponibilidade, itemCardapio.getDisponibilidade());
        assertEquals(fotoPrato, itemCardapio.getFotoPrato());
        assertEquals(restauranteMock, itemCardapio.getRestaurante());
    }

    @Test
    @DisplayName("Deve verificar associação com restaurante")
    void deveVerificarAssociacaoComRestaurante() {
        ItensCardapioEntity itemCardapio = new ItensCardapioEntity();

        assertNull(itemCardapio.getRestaurante());

        itemCardapio.setRestaurante(restauranteMock);

        assertNotNull(itemCardapio.getRestaurante());
        assertEquals(restauranteMock, itemCardapio.getRestaurante());
    }
}