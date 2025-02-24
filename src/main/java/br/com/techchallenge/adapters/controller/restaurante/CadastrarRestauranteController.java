package br.com.techchallenge.adapters.controller.restaurante;

import br.com.techchallenge.application.mapper.RestauranteMapper;
import br.com.techchallenge.domain.Endereco;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.request.RestauranteRequestDto;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurante")
@RequiredArgsConstructor
public class CadastrarRestauranteController {

    private final br.com.techchallenge.adapters.useCaseImpl.restaurante.CadastrarRestauranteUseCase restauranteCadastrarUseCase;
    private final RestauranteMapper mapper;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody RestauranteRequestDto request) {
       try{
           Endereco endereco = new Endereco(
                   request.endereco().getLogradouro(),
                   request.endereco().getNumero(),
                   request.endereco().getComplemento(),
                   request.endereco().getBairro(),
                   request.endereco().getCidade(),
                   request.endereco().getEstado(),
                   request.endereco().getCep()
           );

           Restaurante restaurante = new Restaurante();
           restaurante.setNome(request.nome());
           restaurante.setTipoCozinha(request.tipoCozinha());
           restaurante.setEndereco(endereco);

           restaurante.setEndereco(endereco);

           restauranteCadastrarUseCase.cadastrar(restaurante);

           return new ResponseEntity<>("Restaurante cadastrado com sucesso", HttpStatus.CREATED);
    } catch(
    DataIntegrityViolationException e)

    {
        return new ResponseEntity<>("Restaurante já cadastrado com essas informações", HttpStatus.CONFLICT);
    }
}
}