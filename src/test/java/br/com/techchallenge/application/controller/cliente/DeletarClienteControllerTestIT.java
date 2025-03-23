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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class DeletarClienteControllerTestIT {

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
    void deveDeletarClienteComSucesso() throws Exception {
        mockMvc.perform(delete("/api/cliente-restaurante/deletar/{id}", clienteExistente.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente de Restaurante deletado com sucesso"));

        assertFalse(clienteRepository.existsById(clienteExistente.getId()));
    }

    @Test
    void deveRetornar404_QuandoClienteNaoExiste() throws Exception {
        mockMvc.perform(delete("/api/cliente-restaurante/deletar/999"))
                .andExpect(status().isNotFound());
    }
}
