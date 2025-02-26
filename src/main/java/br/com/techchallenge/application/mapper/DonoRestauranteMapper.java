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
    @Mapping(source = "endereco", target = "endereco", qualifiedByName = "enderecoToString")
    DonoRestauranteEntity toDonoRestauranteEntity(DonoRestaurante dono);

    List<DonoRestaurante> toDonoRestauranteList(List<DonoRestauranteEntity> entities);

    @Named("enderecoToString")
    default String enderecoToString(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return endereco.getLogradouro() + ", " + endereco.getNumero() + " - " + endereco.getCidade();
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