// package br.com.techchallenge.adapters.converter.itensCardapio;

// import br.com.techchallenge.adapters.dto.itensCardapio.ItensCardapioListarIdResponseDTO;
// import br.com.techchallenge.adapters.dto.itensCardapio.ItensCardapioListarTodosResponseDTO;
// import br.com.techchallenge.adapters.dto.itensCardapio.ItensCardapioRequestDTO;
// import br.com.techchallenge.infra.entity.ItensCardapioEntity;
// import org.springframework.stereotype.Component;

// import java.time.LocalDate;
// import java.util.Optional;

// @Component
// public class ItensCardapioDTOConverter {


//     public ItensCardapioEntity dtoParaEntity(ItensCardapioRequestDTO dto) {
//         return dtoParaEntity(null, dto);
//     }

//     public ItensCardapioEntity dtoParaEntity(Long id, ItensCardapioRequestDTO dto) {
//         return Optional.ofNullable(dto)
//                 .map(source -> {
//                     ItensCardapioEntity entity = new ItensCardapioEntity();
//                     entity.setId(id);
//                     entity.setNome(dto.nome());
//                     entity.setDescricao(dto.descricao().toString());
//                     entity.setPreco(dto.preco());
//                     entity.setDisponibilidade(dto.disponibilidade());
//                     entity.setFotoPrato(dto.fotoPrato());
//                     return entity;
//                 })
//                 .orElse(null);
//     }

//     public ItensCardapioListarTodosResponseDTO entityParaListarTodosDto(ItensCardapioEntity entity) {
//         return new ItensCardapioListarTodosResponseDTO(
//                 entity.getId(),
//                 entity.getNome(),
//                 entity.getDescricao(),
//                 entity.getPreco(),
//                 entity.getDisponibilidade(),
//                 entity.getFotoPrato()
//         );
//     }

//     public ItensCardapioListarIdResponseDTO entityParaListarIdDto(ItensCardapioEntity entity) {
//         return new ItensCardapioListarIdResponseDTO(
//             entity.getId(),
//             entity.getNome(),
//             entity.getDescricao(),
//             entity.getPreco(),
//             entity.getDisponibilidade(),
//             entity.getFotoPrato()
//         );
//     }
// }
