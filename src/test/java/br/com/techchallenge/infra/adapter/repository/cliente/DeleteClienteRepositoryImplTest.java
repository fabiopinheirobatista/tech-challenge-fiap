package br.com.techchallenge.infra.adapter.repository.cliente;

import br.com.techchallenge.domain.exception.ClienteNaoEncontradoException;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteClienteRepositoryImplTest {

    @Mock
    private ClienteRestauranteRepository clienteRestauranteRepository;

    @InjectMocks
    private DeleteClienteRepositoryImpl deleteClienteRepository;

    @Test
    @DisplayName("Deve deletar cliente com sucesso quando o ID existir")
    void deveDeletarClienteQuandoIdExistir() throws ClienteNaoEncontradoException {
        Long idCliente = 1L;
        doNothing().when(clienteRestauranteRepository).deleteById(idCliente);

        Boolean resultado = deleteClienteRepository.delete(idCliente);

        assertTrue(resultado);
        verify(clienteRestauranteRepository, times(1)).deleteById(idCliente);
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente não for encontrado")
    void deveLancarExcecaoQuandoClienteNaoEncontrado() {
        Long idCliente = 99L;
        doThrow(new RuntimeException("Cliente não encontrado")).when(clienteRestauranteRepository).deleteById(idCliente);

        ClienteNaoEncontradoException excecao = assertThrows(ClienteNaoEncontradoException.class, () -> {
            deleteClienteRepository.delete(idCliente);
        });

        assertEquals("Cliente de Restaurante não encontrado", excecao.getMessage());
        verify(clienteRestauranteRepository, times(1)).deleteById(idCliente);
    }
}
