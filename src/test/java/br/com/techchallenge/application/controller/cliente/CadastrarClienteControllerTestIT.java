package br.com.techchallenge.application.controller.cliente;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import br.com.techchallenge.infra.repository.ClienteRestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class CadastrarClienteControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRestauranteRepository clienteRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void deveCadastrarClienteComSucesso() throws Exception {
        String jsonRequest = """
            {
                "nome": "Novo Cliente",
                "email": "novo@teste.com",
                "login": "novo_login",
                "senha": "senha_segura"
            }
        """;

        mockMvc.perform(post("/api/cliente-restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente de Restaurante cadastrado com sucesso"));
    }

    @Test
    void deveRetornar409_QuandoEmailDuplicado() throws Exception {
        ClienteRestauranteEntity save = clienteRepository.save(new ClienteRestauranteEntity(null, "Cliente Existente", "duplicado@teste.com", "login_duplicado", "senha"));

        String jsonRequest = """
            {
                "nome": "Cliente Duplicado",
                "email": "duplicado@teste.com",
                "login": "login_duplicado",
                "senha": "senha"
            }
        """;

        mockMvc.perform(post("/api/cliente-restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
