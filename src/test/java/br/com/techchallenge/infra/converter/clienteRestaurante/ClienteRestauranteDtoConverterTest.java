package br.com.techchallenge.infra.converter.clienteRestaurante;

import br.com.techchallenge.domain.input.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteRestauranteDtoConverterTest {

    @InjectMocks
    private ClienteRestauranteDtoConverter converter;

    private ClienteRestauranteRequestDto requestDto;
    private ClienteRestauranteEntity entity;
    private final Long id = 1L;
    private final String nome = "Cliente Teste";
    private final String email = "cliente@teste.com";
    private final String login = "clienteteste";
    private final String senha = "senha123";

    @BeforeEach
    void configurar() {
        requestDto = new ClienteRestauranteRequestDto(nome, email, login, senha);

        entity = new ClienteRestauranteEntity();
        entity.setId(id);
        entity.setNome(nome);
        entity.setEmail(email);
        entity.setLogin(login);
        entity.setSenha(senha);
    }

    @Test
    @DisplayName("Deve converter DTO para Entity sem ID")
    void deveConverterDtoParaEntitySemId() {
        ClienteRestauranteEntity resultado = converter.dtoParaEntity(requestDto);

        assertNotNull(resultado);
        assertNull(resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(email, resultado.getEmail());
        assertEquals(login, resultado.getLogin());
        assertEquals(senha, resultado.getSenha());
    }

    @Test
    @DisplayName("Deve converter DTO para Entity com ID")
    void deveConverterDtoParaEntityComId() {
        ClienteRestauranteEntity resultado = converter.dtoParaEntity(id, requestDto);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals(nome, resultado.getNome());
        assertEquals(email, resultado.getEmail());
        assertEquals(login, resultado.getLogin());
        assertEquals(senha, resultado.getSenha());
    }

    @Test
    @DisplayName("Deve converter Entity para DTO de resposta")
    void deveConverterEntityParaDtoResposta() {
        ClienteRestauranteResponseDto resultado = converter.entityParaDto(entity);

        assertNotNull(resultado);
        assertEquals(id, resultado.id());
        assertEquals(nome, resultado.nome());
        assertEquals(email, resultado.email());
        assertEquals(login, resultado.login());
    }

    @Test
    @DisplayName("Deve retornar nulo ao tentar converter DTO nulo")
    void deveRetornarNuloAoTentarConverterDtoNulo() {
        ClienteRestauranteEntity resultado = converter.dtoParaEntity(null);

        assertNull(resultado);
    }
}