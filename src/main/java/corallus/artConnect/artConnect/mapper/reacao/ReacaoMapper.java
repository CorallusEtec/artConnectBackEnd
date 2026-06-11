package corallus.artConnect.artConnect.mapper.reacao;

import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
import corallus.artConnect.artConnect.entity.Reacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReacaoMapper {
    @Mapping(target = "idPublicacao", source = "publicacao.id")
    ReacaoResponse toDTO(Reacao entity);


    List<ReacaoResponse> toDTOList(List<Reacao> entityList);
}
