package corallus.artConnect.artConnect.mapper.usuario;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.mapper.contato.ContatoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = { ContatoMapper.class })
public interface UsuarioMapper {
    
    @Mapping(source = "contatos", target = "contatos")  
    UsuarioResponse toDTO(Usuario entity);

    List<UsuarioResponse> toDTOList(List<Usuario> entityList);
}