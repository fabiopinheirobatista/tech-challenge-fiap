package br.com.techchallenge.domain.useCase.donoRestaurante;

import br.com.techchallenge.domain.input.donoRestaurante.DonoRestauranteValidarLoginRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.repository.DonoRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidarLoginDonoRestauranteUseCase {

    private final DonoRestauranteRepository donoRestauranteRepository;

    public enum ResultadoValidacao {
        SUCESSO,
        USUARIO_NAO_ENCONTRADO,
        CREDENCIAIS_INVALIDAS
    }

    public ResultadoValidacao execute(DonoRestauranteValidarLoginRequestDTO request) {
        Optional<DonoRestauranteEntity> donoOpt = donoRestauranteRepository.findById(request.getId());

        if (donoOpt.isEmpty()) {
            return ResultadoValidacao.USUARIO_NAO_ENCONTRADO;
        }

        DonoRestauranteEntity dono = donoOpt.get();
        if (!dono.getLogin().equals(request.getLogin()) || !dono.getSenha().equals(request.getSenha())) {
            return ResultadoValidacao.CREDENCIAIS_INVALIDAS;
        }

        return ResultadoValidacao.SUCESSO;
    }
}
