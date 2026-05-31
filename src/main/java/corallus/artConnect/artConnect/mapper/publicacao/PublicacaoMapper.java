package corallus.artConnect.artConnect.mapper.publicacao;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicacaoMapper extends BaseMapper<Publicacao, PublicacaoResponse> {}
