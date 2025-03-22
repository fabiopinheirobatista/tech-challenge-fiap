package br.com.techchallenge.domain.generic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class OutputStatusTest {

    private OutputStatus outputStatus;

    @BeforeEach
    public void setUp() {
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
    }

    @Test
    @DisplayName("Deve retornar o código de status corretamente")
    void deveRetornarCodigoStatusCorretamente() {
        assertEquals(200, outputStatus.getCode());
    }

}