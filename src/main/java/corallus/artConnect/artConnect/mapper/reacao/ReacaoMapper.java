package corallus.artConnect.artConnect.mapper.reacao;

import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReacaoMapper extends BaseMapper<Reacao, ReacaoResponse> {}
