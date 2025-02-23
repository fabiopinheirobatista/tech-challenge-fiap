package br.com.techchallenge.application.mapper;

import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.request.RestauranteRequestDto;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {
    RestauranteEntity toRestauranteEntity(Restaurante restaurante);
    Restaurante toRestaurante(RestauranteEntity entity);
    Restaurante toRestauranteRequestDto(RestauranteRequestDto dto);
}