package br.com.techchallenge.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ItensCardapioTest {

    @Test
    @DisplayName("Deve criar item do cardápio com sucesso")
    void deveCriarItemDoCardapioComSucesso() {
        Long id = 1L;
        String nome = "Feijoada Completa";
        String descricao = "Feijoada tradicional com acompanhamentos";
        double preco = 45.90;
        String disponibilidade = "DISPONIVEL";
        String fotoPrato = "feijoada.jpg";
        Long idRestaurante = 5L;

        ItensCardapio itemCardapio = new ItensCardapio(id, nome, descricao, preco, disponibilidade, fotoPrato, idRestaurante);

        assertNotNull(itemCardapio);
        assertEquals(id, itemCardapio.getId());
        assertEquals(nome, itemCardapio.getNome());
        assertEquals(descricao, itemCardapio.getDescricao());
        assertEquals(preco, itemCardapio.getPreco());
        assertEquals(disponibilidade, itemCardapio.getDisponibilidade());
        assertEquals(fotoPrato, itemCardapio.getFotoPrato());
        assertEquals(idRestaurante, itemCardapio.getIdRestaurante());
    }

    @Test
    @DisplayName("Deve atualizar dados do item do cardápio")
    void deveAtualizarDadosDoItemDoCardapio() {
        ItensCardapio itemCardapio = new ItensCardapio();

        Long id = 1L;
        String nomeInicial = "Feijoada Completa";
        String descricaoInicial = "Feijoada tradicional";
        double precoInicial = 45.90;
        String disponibilidadeInicial = "DISPONIVEL";
        String fotoPratoInicial = "feijoada.jpg";
        Long idRestauranteInicial = 5L;

        itemCardapio.setId(id);
        itemCardapio.setNome(nomeInicial);
        itemCardapio.setDescricao(descricaoInicial);
        itemCardapio.setPreco(precoInicial);
        itemCardapio.setDisponibilidade(disponibilidadeInicial);
        itemCardapio.setFotoPrato(fotoPratoInicial);
        itemCardapio.setIdRestaurante(idRestauranteInicial);

        assertEquals(id, itemCardapio.getId());
        assertEquals(nomeInicial, itemCardapio.getNome());
        assertEquals(descricaoInicial, itemCardapio.getDescricao());
        assertEquals(precoInicial, itemCardapio.getPreco());
        assertEquals(disponibilidadeInicial, itemCardapio.getDisponibilidade());
        assertEquals(fotoPratoInicial, itemCardapio.getFotoPrato());
        assertEquals(idRestauranteInicial, itemCardapio.getIdRestaurante());

        String novoNome = "Feijoada Completa Especial";
        String novaDescricao = "Feijoada tradicional com acompanhamentos premium";
        double novoPreco = 59.90;
        String novaDisponibilidade = "INDISPONIVEL";
        String novaFotoPrato = "feijoada_especial.jpg";
        Long novoIdRestaurante = 8L;

        itemCardapio.setNome(novoNome);
        itemCardapio.setDescricao(novaDescricao);
        itemCardapio.setPreco(novoPreco);
        itemCardapio.setDisponibilidade(novaDisponibilidade);
        itemCardapio.setFotoPrato(novaFotoPrato);
        itemCardapio.setIdRestaurante(novoIdRestaurante);

        assertEquals(id, itemCardapio.getId());
        assertEquals(novoNome, itemCardapio.getNome());
        assertEquals(novaDescricao, itemCardapio.getDescricao());
        assertEquals(novoPreco, itemCardapio.getPreco());
        assertEquals(novaDisponibilidade, itemCardapio.getDisponibilidade());
        assertEquals(novaFotoPrato, itemCardapio.getFotoPrato());
        assertEquals(novoIdRestaurante, itemCardapio.getIdRestaurante());
    }
}