package br.com.techchallenge.application.controller.donoRestaurante;

import br.com.techchallenge.infra.converter.donoRestaurante.DonoRestauranteDTOConverter;
import br.com.techchallenge.infra.service.DonoRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/donos-restaurante")
public class DonoRestauranteExcluirController {

    private final DonoRestauranteDTOConverter converter;
    private final DonoRestauranteService service;

    public DonoRestauranteExcluirController(DonoRestauranteDTOConverter converter, DonoRestauranteService service) {
        this.converter = converter;
        this.service = service;
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Dono de Restaurante deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro ao deletar Dono de Restaurante");
        }
    }

}
