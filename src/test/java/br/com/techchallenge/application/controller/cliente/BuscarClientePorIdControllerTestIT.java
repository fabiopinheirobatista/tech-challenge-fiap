package br.com.techchallenge.application.controller.cliente;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class BuscarClientePorIdControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRestauranteRepository clienteRepository;

    private ClienteRestauranteEntity clienteExistente;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        clienteExistente = clienteRepository.save(
                new ClienteRestauranteEntity(null, "Cliente Teste", "cliente@teste.com", "login_teste", "senha_teste")
        );
    }

    @Test
    void deveRetornarClientePorId() throws Exception {
        mockMvc.perform(get("/api/cliente-restaurante/listar/{id}", clienteExistente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Cliente Teste"))
                .andExpect(jsonPath("$.email").value("cliente@teste.com"));
    }

    @Test
    void deveRetornar404_QuandoClienteNaoExiste() throws Exception {
        mockMvc.perform(get("/api/cliente-restaurante/listar/999"))
                .andExpect(status().isNotFound());
    }
}
