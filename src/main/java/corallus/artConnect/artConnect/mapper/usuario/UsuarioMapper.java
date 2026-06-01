package corallus.artConnect.artConnect.mapper.usuario;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponse toDTO(Usuario entity);

    List<UsuarioResponse> toDTOList(List<Usuario> entityList);
}
