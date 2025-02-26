package br.com.techchallenge.application.mapper;

import br.com.techchallenge.domain.DonoRestaurante;
import br.com.techchallenge.domain.Endereco;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DonoRestauranteMapper {
    @Mapping(source = "endereco", target = "endereco")
    DonoRestauranteEntity toDonoRestauranteEntity(DonoRestaurante dono);

    List<DonoRestaurante> toDonoRestauranteList(List<DonoRestauranteEntity> entities);

    @Named("enderecoToString")
    default String enderecoToString(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return endereco.getLogradouro() + ", " + endereco.getNumero() + " - " + endereco.getCidade();
    }
}