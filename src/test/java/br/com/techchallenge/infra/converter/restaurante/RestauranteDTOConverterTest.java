package br.com.techchallenge.infra.converter.restaurante;

import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.input.restaurante.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestauranteDTOConverterTest {

    private RestauranteDTOConverter converter;

    @BeforeEach
    void setUp() {
        converter = new RestauranteDTOConverter();
    }

    @DisplayName("Conversão de DTO para Entity com dados válidos")
    @Test
    void dtoParaEntity_DeveConverterCorretamente() {
        // Arrange
        RestauranteRequestDTO dto = new RestauranteRequestDTO(
                "Restaurante Teste",
                new Endereco("Rua Teste", "123", "Bairro Teste", "Cidade Teste", "UF"),
                "Italiana",
                1L
        );

        // Act
        RestauranteEntity entity = converter.dtoParaEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Restaurante Teste", entity.getNome());
        assertEquals("Italiana", entity.getTipoCozinha());
        assertEquals("Rua Teste", entity.getEndereco().getLogradouro());
        assertEquals(1L, entity.getDonoRestaurante().getId());
    }

    @Test
    @DisplayName("Conversão de Entity para ListarTodosDTO")
    void entityParaListarTodosDto_DeveConverterCorretamente() {
        // Arrange
        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(1L);
        entity.setNome("Restaurante Teste");
        entity.setEndereco(new Endereco("Rua Teste", "123", "Bairro Teste", "Cidade Teste", "UF"));
        entity.setTipoCozinha("Mexicana");

        // Act
        var dto = converter.entityParaListarTodosDto(entity);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Restaurante Teste", dto.nome());
        assertEquals("Mexicana", dto.tipoCozinha());
    }

    @Test
    @DisplayName("Conversão de Entity para ResponseDTO com Dono")
    void entityParaResponseDto_DeveIncluirDonoCorretamente() {
        // Arrange
        DonoRestauranteEntity donoEntity = new DonoRestauranteEntity(
                1L, "Dono Teste", "Rua X", "dono@teste.com", "login_dono", "senha", null
        );

        RestauranteEntity entity = new RestauranteEntity();
        entity.setId(2L);
        entity.setDonoRestaurante(donoEntity);

        // Act
        var responseDto = converter.entityParaResponseDto(entity);

        // Assert
        assertEquals(1L, responseDto.donoRestaurante().nome());
    }

    @Test
    @DisplayName("Conversão de Entity para Restaurante (Domínio)")
    void restauranteEntityToRestaurante_DeveConverterRelacionamentos() {
        // Arrange
        DonoRestauranteEntity donoEntity = new DonoRestauranteEntity(
                1L, "Dono", "Rua Y", "dono@exemplo.com", "login", "senha", null
        );

        RestauranteEntity entity = new RestauranteEntity();
        entity.setDonoRestaurante(donoEntity);
        entity.setTipoCozinha("Japonesa");

        // Act
        Restaurante restaurante = converter.restauranteEntityToRestaurante(entity);

        // Assert
        assertEquals("Japonesa", restaurante.getTipoCozinha());
        assertEquals(1L, restaurante.getDonoRestaurante().getId());
        assertEquals("Dono", restaurante.getDonoRestaurante().getNome());
    }

    @Test
    @DisplayName("Conversão de DTO para Restaurante (Domínio) com ID do Dono")
    void dtoToRestaurante_DeveMapearIdDonoCorretamente() {
        // Arrange
        RestauranteRequestDTO dto = new RestauranteRequestDTO(
                "Nome Restaurante",
                new Endereco("Rua Teste", "123", "Bairro Teste", "Cidade Teste", "UF"),
                "Árabe",
                5L
        );

        // Act
        Restaurante restaurante = converter.dtoToRestaurante(dto);

        // Assert
        assertEquals(5L, restaurante.getDonoRestaurante().getId());
        assertEquals("Árabe", restaurante.getTipoCozinha());
    }

    @Test
    @DisplayName("Conversão com DTO nulo")
    void dtoParaEntity_DeveRetornarNullQuandoDtoNulo() {
        assertNull(converter.dtoParaEntity(null));
    }
}