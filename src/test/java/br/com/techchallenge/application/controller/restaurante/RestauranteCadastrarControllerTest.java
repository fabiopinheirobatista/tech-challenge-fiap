package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.exception.DonoRestauranteNaoExisteException;
import br.com.techchallenge.domain.exception.RestauranteJaCadastradoException;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.domain.output.restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.domain.useCase.restaurante.CadastrarRestauranteUseCase;
import br.com.techchallenge.infra.converter.restaurante.RestauranteDTOConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RestauranteCadastrarControllerTest {

    @Mock
    private RestauranteDTOConverter restaurantesConverter;

    @Mock
    private CadastrarRestauranteUseCase cadastrarRestauranteUseCase;

    @InjectMocks
    private RestauranteCadastrarController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar201_QuandoCadastroValido() throws RestauranteJaCadastradoException, DonoRestauranteNaoExisteException {
        RestauranteRequestDTO request = new RestauranteRequestDTO("Novo Restaurante", null, "Brasileira", 1L);
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setNome("Novo Restaurante");
        restaurante.setTipoCozinha("Brasileira");

        RestauranteListarTodosResponseDTO responseDTO = new RestauranteListarTodosResponseDTO(1L, "Novo Restaurante", null, "Brasileira");

        when(restaurantesConverter.dtoToRestaurante(any(RestauranteRequestDTO.class))).thenReturn(restaurante);
        when(cadastrarRestauranteUseCase.execute(any(Restaurante.class))).thenReturn(restaurante);
        when(restaurantesConverter.restauranteParaResponseDto(any(Restaurante.class))).thenReturn(responseDTO);

        ResponseEntity<?> response = controller.cadastrar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }
}