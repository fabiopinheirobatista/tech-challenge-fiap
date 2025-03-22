package br.com.techchallenge.domain.output.donoRestaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteListarTodosResponseDTOTest {

    private List<DonoRestauranteListarTodosResponseDTO> responseDTOs;

    @BeforeEach
    public void setUp() {
        responseDTOs = new ArrayList<>();
        responseDTOs.add(new DonoRestauranteListarTodosResponseDTO(1L, "Nome do Dono 1", "Endereço do Dono 1", "Email do Dono 1", "Login do Dono 1"));
        responseDTOs.add(new DonoRestauranteListarTodosResponseDTO(2L, "Nome do Dono 2", "Endereço do Dono 2", "Email do Dono 2", "Login do Dono 2"));
    }

    @Test
    @DisplayName("Deve retornar a lista de donos de restaurantes corretamente")
    void deveRetornarListaDeDonosCorretamente() {
        assertEquals(2, responseDTOs.size());
        assertEquals(1L, responseDTOs.get(0).getId());
        assertEquals("Nome do Dono 1", responseDTOs.get(0).getNome());
        assertEquals("Endereço do Dono 1", responseDTOs.get(0).getEndereco());
        assertEquals("Email do Dono 1", responseDTOs.get(0).getEmail());
        assertEquals("Login do Dono 1", responseDTOs.get(0).getLogin());

        assertEquals(2L, responseDTOs.get(1).getId());
        assertEquals("Nome do Dono 2", responseDTOs.get(1).getNome());
        assertEquals("Endereço do Dono 2", responseDTOs.get(1).getEndereco());
        assertEquals("Email do Dono 2", responseDTOs.get(1).getEmail());
        assertEquals("Login do Dono 2", responseDTOs.get(1).getLogin());
    }
}