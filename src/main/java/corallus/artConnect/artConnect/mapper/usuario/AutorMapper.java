package corallus.artConnect.artConnect.mapper.usuario;

import corallus.artConnect.artConnect.dto.response.usuario.AutorResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AutorMapper {
    AutorResponse toDTO(Usuario entity);

    List<AutorResponse> toDTOList(List<Usuario> entityList);
}
