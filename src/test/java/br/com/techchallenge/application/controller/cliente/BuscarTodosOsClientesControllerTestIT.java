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
class BuscarTodosOsClientesControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRestauranteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void deveListarTodosClientes() throws Exception {
        clienteRepository.save(new ClienteRestauranteEntity(null, "Cliente 1", "cliente1@teste.com", "login1", "senha1"));
        clienteRepository.save(new ClienteRestauranteEntity(null, "Cliente 2", "cliente2@teste.com", "login2", "senha2"));

        mockMvc.perform(get("/api/cliente-restaurante/buscar-todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deveRetornar404_QuandoNenhumClienteCadastrado() throws Exception {
        mockMvc.perform(get("/api/cliente-restaurante/buscar-todos"))
                .andExpect(status().isNotFound());
    }
}
