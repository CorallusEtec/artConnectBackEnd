package corallus.artConnect.artConnect.mapper.tipoContato;

import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.entity.contato.TipoContato;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoContatoMapper extends BaseMapper<TipoContato, TipoContatoResponse> {}
