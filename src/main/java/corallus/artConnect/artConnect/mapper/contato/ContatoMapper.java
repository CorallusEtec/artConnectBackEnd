package corallus.artConnect.artConnect.mapper.contato;

import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContatoMapper extends BaseMapper<Contato, ContatoResponse> {
}
