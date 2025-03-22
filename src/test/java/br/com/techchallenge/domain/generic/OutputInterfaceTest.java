package br.com.techchallenge.domain.generic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OutputInterfaceTest {

    private OutputInterface outputInterface;

    @BeforeEach
    public void setUp() {
        outputInterface = new OutputInterface() {
            @Override
            public Object getBody() {
                return "Resultado da operação";
            }

            @Override
            public OutputStatus getOutputStatus() {
                return new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
            }
        };
    }

    @Test
    @DisplayName("Deve retornar o corpo da resposta não nulo")
    void deveRetornarCorpoRespostaNaoNulo() {
        assertNotNull(outputInterface.getBody());
    }

    @Test
    @DisplayName("Deve retornar o status de saída não nulo")
    void deveRetornarStatusSaidaNaoNulo() {
        assertNotNull(outputInterface.getOutputStatus());
    }
}