package br.com.techchallenge.adapters.converter;

import br.com.techchallenge.adapters.dto.donoRestaurante.DonoRestauranteRequestDTO;
import br.com.techchallenge.infra.entity.DonoRestauranteEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DonoRestauranteDTOConverter {


    public DonoRestauranteEntity dtoParaEntity(DonoRestauranteRequestDTO dto) {
        return dtoParaEntity(null, dto);
    }

    public DonoRestauranteEntity dtoParaEntity(Long id, DonoRestauranteRequestDTO dto) {
        return Optional.ofNullable(dto)
                .map(source -> {
                    DonoRestauranteEntity entity = new DonoRestauranteEntity();
                    entity.setId(id);
                    entity.setNome(dto.nome());
                    entity.setEmail(dto.email());
                    entity.setEndereco(dto.endereco().toString());
                    entity.setLogin(dto.login());
                    entity.setSenha(dto.senha());
                    entity.setDataUltimaAlteracao(LocalDate.now());
                    return entity;
                })
                .orElse(null);
    }
}
