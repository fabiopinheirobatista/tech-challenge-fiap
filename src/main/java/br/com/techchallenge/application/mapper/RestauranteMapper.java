package br.com.techchallenge.application.mapper;

import br.com.techchallenge.domain.Endereco;
import br.com.techchallenge.domain.Restaurante;
import br.com.techchallenge.infra.dto.restaurante.request.RestauranteRequestDto;
import br.com.techchallenge.infra.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    @Mapping(source = "endereco", target = "endereco", qualifiedByName = "enderecoToString")
    RestauranteEntity toEntity(Restaurante restaurante);

    @Mapping(source = "endereco", target = "endereco", qualifiedByName = "stringToEndereco")
    Restaurante toDomain(RestauranteEntity entity);

    @Named("enderecoToString")
    default String enderecoToString(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return endereco.getRua() + ", " + endereco.getNumero() + " - " + endereco.getCidade();
    }

    @Named("stringToEndereco")
    default Endereco stringToEndereco(String enderecoStr) {
        if (enderecoStr == null || enderecoStr.isEmpty()) {
            return null;
        }

        String[] partes = enderecoStr.split(",");
        if (partes.length < 2) {
            return null;
        }
        String rua = partes[0].trim();
        String[] subPartes = partes[1].split("-");
        String numero = subPartes[0].trim();
        String cidade = subPartes.length > 1 ? subPartes[1].trim() : "";
        return new Endereco(rua, numero, cidade);
    }
}