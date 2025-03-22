package br.com.techchallenge.domain.gateway.clienteRestaurante;

import br.com.techchallenge.domain.entity.ClienteRestaurante;
import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SalvarClienteInterfaceTest {

    @Mock
    private SalvarClienteInterface salvarClienteInterface;

    private ClienteRestauranteEntity clienteParaSalvar;
    private ClienteRestauranteEntity clienteSalvo;

    @BeforeEach
    public void setUp() {
        clienteParaSalvar = new ClienteRestauranteEntity();
        clienteSalvo = new ClienteRestauranteEntity();
    }

    @Test
    @DisplayName("Deve salvar um novo cliente com sucesso")
    void deveSalvarNovoClienteComSucesso() {

        ClienteRestaurante resultado = salvarClienteInterface.cadastrar(clienteParaSalvar);

        assertEquals(clienteSalvo, resultado);
        verify(salvarClienteInterface).cadastrar(clienteParaSalvar);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tenta atualizar um cliente inexistente")
    void deveLancarExcecaoQuandoTentaAtualizarClienteInexistente() {

        assertThrows(ClienteNaoEncontradoException.class, () -> salvarClienteInterface.atualizar(clienteParaSalvar));
    }
}