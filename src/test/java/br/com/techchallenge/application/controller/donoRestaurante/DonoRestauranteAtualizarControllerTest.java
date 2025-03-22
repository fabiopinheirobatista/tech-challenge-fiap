package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.domain.useCase.donoRestaurante.BuscarPorIdDonoRestauranteUseCase;
import br.com.techchallenge.domain.useCase.donoRestaurante.SalvarDonoRestauranteUseCase;
import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DonoRestauranteAtualizarControllerTest {

    @Mock
    private DonoRestauranteDTOConverter converter;

    @Mock
    private SalvarDonoRestauranteUseCase salvarDonoRestauranteUseCase;

    @Mock
    private BuscarPorIdDonoRestauranteUseCase buscarPorIdDonoRestauranteUseCase;

    @InjectMocks
    private DonoRestauranteAtualizarController controller;

    @Test
    @DisplayName("Deve retornar erro interno quando ocorrer exceção durante atualização")
    void deveRetornarErroInternoQuandoOcorrerExcecaoDuranteAtualizacao() {
        Long id = 1L;
        // Corrigindo os parâmetros passados para o construtor DonoRestauranteRequestDTO
        DonoRestauranteRequestDTO requestDTO = new DonoRestauranteRequestDTO(
                id,
                "Dono Teste",
                new Endereco("Rua Teste", "123", "Complemento", "Bairro", "Cidade", "Estado", "12345-678"),
                "11999999999",
                "email@teste.com"
        );

        DonoRestauranteEntity existingDono = new DonoRestauranteEntity();
        existingDono.setId(id);

        DonoRestauranteEntity updatedDono = new DonoRestauranteEntity();
        updatedDono.setId(id);

        when(buscarPorIdDonoRestauranteUseCase.findById(id)).thenReturn(existingDono);
        when(converter.dtoParaEntity(id, requestDTO)).thenReturn(updatedDono);
        doThrow(new RuntimeException("Erro ao salvar")).when(salvarDonoRestauranteUseCase).cadastrar(any());

        ResponseEntity<String> response = controller.atualizar(id, requestDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao atualizar Dono de Restaurante", response.getBody());
    }
}