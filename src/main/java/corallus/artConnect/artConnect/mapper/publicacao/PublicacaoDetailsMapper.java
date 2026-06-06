package corallus.artConnect.artConnect.mapper.publicacao;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoDetailsResponse;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicacaoDetailsMapper {
    PublicacaoDetailsResponse toDTO(Publicacao entity);

    List<PublicacaoDetailsResponse> toDTOList(List<Publicacao> entityList);
}
