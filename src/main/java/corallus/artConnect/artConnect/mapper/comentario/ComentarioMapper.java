package corallus.artConnect.artConnect.mapper.comentario;

import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.entity.Comentario;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ComentarioMapper extends BaseMapper<Comentario, ComentarioResponse> {
}
