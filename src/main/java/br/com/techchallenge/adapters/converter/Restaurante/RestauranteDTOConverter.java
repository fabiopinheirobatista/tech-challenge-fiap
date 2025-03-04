package br.com.techchallenge.adapters.converter.Restaurante;

import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteSimplesResponseDto;
import br.com.techchallenge.adapters.dto.Restaurante.*;
import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteResponseDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestauranteDTOConverter {

    public RestauranteEntity dtoParaEntity(RestauranteRequestDTO dto) {
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

    public RestauranteResponseDTO entityParaResponseDto(RestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new RestauranteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getTipoCozinha(),
                donoEntityParaSimplesResponseDto(entity.getDonoRestaurante())
        );
    }

    private DonoRestauranteResponseDTO donoEntityParaResponseDto(DonoRestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DonoRestauranteResponseDTO(
                entity.getId(),
                entity.getNome(),
                entity.getEndereco(),
                entity.getEmail(),
                entity.getLogin()
        );
    }

    private DonoRestauranteSimplesResponseDto donoEntityParaSimplesResponseDto(DonoRestauranteEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DonoRestauranteSimplesResponseDto(
                entity.getNome(),
                entity.getEmail(),
                entity.getEndereco()
        );
    }
}
