package br.com.techchallenge.adapters.converter.Restaurante;

import br.com.techchallenge.adapters.dto.Restaurante.RestauranteListarIdResponseDTO;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteListarTodosResponseDTO;
import br.com.techchallenge.adapters.dto.Restaurante.RestauranteRequestDTO;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestauranteDTOConverter {

    public RestauranteEntity converter(RestauranteRequestDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> {
                    RestauranteEntity entity = new RestauranteEntity();
                    entity.setNome(dto.nome());
                    entity.setEndereco(dto.endereco());
                    entity.setTipoCozinha(dto.tipoCozinha());
                    return entity;
                })
                .orElse(null);
    }

    public RestauranteListarTodosResponseDTO entityParaListarTodosDto(RestauranteEntity entity) {
        return new RestauranteListarTodosResponseDTO(
                entity.getNome(),
                entity.getEndereco().toString(),
                entity.getTipoCozinha()
        );
    }

    public RestauranteListarIdResponseDTO entityParaListarIdDto(RestauranteEntity entity) {
        return new RestauranteListarIdResponseDTO(
                entity.getNome(),
                entity.getEndereco().toString(),
                entity.getTipoCozinha()
        );
    }
}
