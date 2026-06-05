package corallus.artConnect.artConnect.mapper.publicacao;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.service.PublicacaoService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PublicacaoMapper {

    PublicacaoResponse toDTO(Publicacao entity);

    List<PublicacaoResponse> toDTOList(List<Publicacao> entityList);

}
