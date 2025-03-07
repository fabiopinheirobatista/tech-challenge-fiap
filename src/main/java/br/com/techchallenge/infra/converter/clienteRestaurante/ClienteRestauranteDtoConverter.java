package br.com.techchallenge.infra.converter.clienteRestaurante;

import br.com.techchallenge.domain.input.clienteRestaurante.ClienteRestauranteRequestDto;
import br.com.techchallenge.domain.output.clienteRestaurante.ClienteRestauranteResponseDto;
import br.com.techchallenge.infra.entity.ClienteRestauranteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRestauranteDtoConverter {

    public ClienteRestauranteEntity dtoParaEntity(ClienteRestauranteRequestDto dto) {
        return dtoParaEntity(null, dto);
    }

    public ClienteRestauranteEntity dtoParaEntity(Long id, ClienteRestauranteRequestDto dto) {
        return Optional.ofNullable(dto)
                .map(source -> {
                    ClienteRestauranteEntity entity = new ClienteRestauranteEntity();
                    entity.setId(id);
                    entity.setNome(dto.nome());
                    entity.setEmail(dto.email());
                    entity.setLogin(dto.login());
                    entity.setSenha(dto.senha());
                    return entity;
                })
                .orElse(null);
    }

    public ClienteRestauranteResponseDto entityParaDto(ClienteRestauranteEntity entity) {
        return new ClienteRestauranteResponseDto(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getLogin()
        );
    }
}
