package br.com.techchallenge.domain.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RestauranteTest {

    @Mock
    private Endereco endereco;

    @Mock
    private DonoRestaurante donoRestaurante;

    @Test
    @DisplayName("Deve atualizar dados do restaurante com sucesso")
    void deveAtualizarDadosDoRestauranteComSucesso() {
        Long id = 5L;
        String nome = "Cantina Bella";
        String enderecoTexto = "Avenida Paulista, 1500";
        Restaurante restaurante = new Restaurante(id, nome, enderecoTexto);

        String novoNome = "Cantina Luxo";
        String novoEnderecoTexto = "Rua dos Pinheiros, 200";

        Endereco enderecoCriado = restaurante.getEndereco();
    }
}