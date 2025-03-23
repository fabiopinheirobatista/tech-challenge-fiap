package br.com.techchallenge.application.controller.cliente;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
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
class AtualizarClienteControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRestauranteRepository clienteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private ClienteRestauranteEntity clienteExistente;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        clienteExistente = clienteRepository.save(
                new ClienteRestauranteEntity(
                        null, "Cliente Teste", "cliente@teste.com", "login_teste", "senha_teste"
                )
        );
    }

    @Test
    void deveAtualizarClienteComSucesso() throws Exception {
        String novoNome = "Cliente Atualizado";
        String jsonRequest = """
            {
                "nome": "%s",
                "email": "cliente@teste.com",
                "login": "login_teste",
                "senha": "nova_senha"
            }
        """.formatted(novoNome);

        mockMvc.perform(put("/api/cliente-restaurante/atualizar/{id}", clienteExistente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente de Restaurante atualizado com sucesso"));

    }

    @Test
    void deveRetornar404_QuandoClienteNaoEncontrado() throws Exception {
        mockMvc.perform(put("/api/cliente-restaurante/atualizar/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteExistente)))
                .andExpect(status().isNotFound());
    }
}