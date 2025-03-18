package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.RestauranteNaoEncontradoException;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.AtualizarRestauranteUseCase;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestauranteAlterarControllerTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteDTOConverter converter;

    @Mock
    private AtualizarRestauranteUseCase useCase;

    @InjectMocks
    private RestauranteAlterarController controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deveRetornar200_QuandoAtualizarComSucesso() throws RestauranteNaoEncontradoException {
        RestauranteRequestDTO requestDTO = new RestauranteRequestDTO("Restaurante Atualizado", null, "Japonesa", 1L);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Restaurante Atualizado");
        restaurante.setTipoCozinha("Japonesa");
        Endereco endereco = new Endereco();

        when(converter.dtoToRestaurante(any(RestauranteRequestDTO.class))).thenReturn(restaurante);
        when(useCase.execute(anyLong(), any(Restaurante.class))).thenReturn(restaurante);
        when(converter.restauranteParaResponseDto(any(Restaurante.class))).thenReturn(new RestauranteListarTodosResponseDTO(1L, "Restaurante Atualizado", endereco, "Japonesa"));

        ResponseEntity<?> response = controller.atualizar(1L, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        RestauranteListarTodosResponseDTO responseBody = (RestauranteListarTodosResponseDTO) response.getBody();
        assertEquals("Restaurante Atualizado", responseBody.nome());
        assertEquals("Japonesa", responseBody.tipoCozinha());
    }

    @Test
    void deveRetornar404_QuandoRestauranteNaoExiste() throws RestauranteNaoEncontradoException {
        RestauranteRequestDTO requestDTO = new RestauranteRequestDTO("Restaurante Inexistente", null, "Chinesa", 99L);

        when(converter.dtoToRestaurante(any(RestauranteRequestDTO.class))).thenReturn(new Restaurante());
        when(useCase.execute(anyLong(), any(Restaurante.class)))
                .thenThrow(new RestauranteNaoEncontradoException("Restaurante não encontrado"));

        RestauranteNaoEncontradoException exception = assertThrows(RestauranteNaoEncontradoException.class,
                () -> controller.atualizar(99L, requestDTO));

        assertEquals("Restaurante não encontrado", exception.getMessage());
    }


}