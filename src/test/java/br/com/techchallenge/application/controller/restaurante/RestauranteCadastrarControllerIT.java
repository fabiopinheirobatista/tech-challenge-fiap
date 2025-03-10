package br.com.techchallenge.application.controller.restaurante;

import br.com.techchallenge.TechChallengeApplication;
import br.com.techchallenge.domain.entity.Restaurante;
import br.com.techchallenge.domain.gateway.RestauranteSalvarInterface;
import br.com.techchallenge.domain.useCase.donoRestaurante.DonoRestaurante;
import br.com.techchallenge.infra.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TechChallengeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class RestauranteCadastrarControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Mock
    private RestauranteSalvarInterface repositorySalvar;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() throws Exception {
        restauranteRepository.deleteAll();
    }

    @Test
    void deveRetornar201_QuandoCadastroSucesso() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("nome", "Restaurante Ososhiro3");
        data.put("idDonoRestaurante", 1);
        data.put("tipoCozinha", "oriental");

        Map<String, String> endereco = new HashMap<>();
        endereco.put("rua", "Rua Exemplo");
        endereco.put("numero", "123");
        endereco.put("complemento", "complemento");
        endereco.put("bairro", "bairro");
        endereco.put("cidade", "Cidade Exemplo");
        endereco.put("estado", "Estado Exemplo");
        endereco.put("cep", "12345-678");

        data.put("endereco", endereco);

        // Converte o objeto Map para uma string JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(data);

        // Imprime a string JSON
        System.out.println(jsonString);
        DonoRestaurante donoRestaurante = new DonoRestaurante();
        donoRestaurante.setId(1L);
        when(repositorySalvar.buscarPorIdDonoRestaurante(anyLong())).thenReturn(Optional.of(donoRestaurante));

        //when(repositorySalvar.buscarPorIdDonoRestaurante(anyLong()).t.thenReturn(new Restaurante(1L, "Restaurante Ososhiro3", null, "oriental", null));
        mockMvc.perform(post("/api/restaurante/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Restaurante Ososhiro3"));
    }
}
