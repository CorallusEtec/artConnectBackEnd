package corallus.artConnect.artConnect.mapper.tipoContato;

import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.entity.contato.TipoContato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoContatoMapper {
    
    @Mapping(source = "id", target = "idTipoContato")
    @Mapping(source = "tipoContato", target = "tipoContato")
    TipoContatoResponse toDTO(TipoContato entity);
    
    List<TipoContatoResponse> toDTOList(List<TipoContato> entityList);
}