package br.com.techchallenge.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class OpenApiConfigTest {

    @InjectMocks
    private OpenApiConfig openApiConfig;

    @Test
    @DisplayName("Deve criar configuração OpenAPI com informações corretas")
    void deveCriarConfiguracaoOpenAPIComInformacoesCorretas() {
        OpenAPI resultado = openApiConfig.locaTech();

        assertNotNull(resultado);
        assertNotNull(resultado.getInfo());

        Info info = resultado.getInfo();
        assertEquals("Loca Tech API", info.getTitle());
        assertEquals("Projeto desenvolvido para o Tech Challenge da FIAP - FASE 2", info.getDescription());

        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("Apache 2.0", license.getName());
        assertEquals("https://github.com", license.getUrl());
    }
}