package br.com.techchallenge.application.mapper;

import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.request.RestauranteRequestDto;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonoRestauranteMapper {
    DonoRestauranteEntity toDonoRestauranteEntity(DonoRestaurante dono);
    DonoRestaurante toDonoRestaurante(DonoRestauranteEntity entity);
    List<DonoRestaurante> toDonoRestauranteList(List<DonoRestauranteEntity> entities);

    DonoRestaurante toRestauranteRequestDto(RestauranteRequestDto dto);
}