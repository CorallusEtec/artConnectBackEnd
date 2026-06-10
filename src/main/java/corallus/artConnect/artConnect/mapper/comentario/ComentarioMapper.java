package corallus.artConnect.artConnect.mapper.comentario;

import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.entity.Comentario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    ComentarioResponse toDTO(Comentario entity);

    List<ComentarioResponse> toDTOList(List<Comentario> entityList);
}
