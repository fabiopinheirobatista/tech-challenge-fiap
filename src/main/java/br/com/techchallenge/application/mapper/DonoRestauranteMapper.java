package br.com.techchallenge.application.mapper;

import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DonoRestauranteMapper {
    DonoRestauranteEntity toDonoRestauranteEntity(DonoRestaurante dono);
    DonoRestaurante toDonoRestaurante(DonoRestauranteEntity entity);
}