package corallus.artConnect.artConnect.mapper.contato;

import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.mapper.tipoContato.TipoContatoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = { TipoContatoMapper.class })
public interface ContatoMapper {
    
    @Mapping(source = "id", target = "idContato")
    @Mapping(source = "tipoContato", target = "tipoContato")
    @Mapping(source = "valorContato", target = "valorContato")
    ContatoResponse toDTO(Contato entity);
    
    List<ContatoResponse> toDTOList(List<Contato> entityList);
}