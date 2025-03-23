package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.Endereco;
import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DonoRestauranteAtualizarControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonoRestauranteRepository donoRestauranteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private DonoRestauranteEntity donoExistente;

    @BeforeEach
    void setUp() {
        donoRestauranteRepository.deleteAll();
        donoExistente = donoRestauranteRepository.save(new DonoRestauranteEntity(
                null,
                "Carlos Oliveira",
                "Av. Paulista, 1000",
                "carlos@example.com",
                "carlos.oliveira",
                "Senha@123",
                null
        ));
    }

    @Test
    void deveAtualizarDonoRestaurante() throws Exception {
        DonoRestauranteRequestDTO request = new DonoRestauranteRequestDTO(
                donoExistente.getId(), "Carlos Silva", new Endereco("Rua Nova", "100", "Apt 202", "Centro", "São Paulo", "SP", "01000-000"), "novo.email@example.com", "novo.login"
        );

        mockMvc.perform(put("/api/donos-restaurante/atualizar/" + donoExistente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Dono de Restaurante atualizado com sucesso!"));
    }

    @Test
    void deveRetornar404_QuandoDonoNaoEncontrado() throws Exception {
        DonoRestauranteRequestDTO request = new DonoRestauranteRequestDTO(
                999L,
                "Nome Inválido",
                new Endereco("Rua X", "1", null, "Bairro Y", "Cidade Z", "ZZ", "00000-000"),
                "email@invalido.com",
                "login_invalido"
        );

        mockMvc.perform(put("/api/donos-restaurante/atualizar/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Dono de Restaurante não encontrado"));
    }
}