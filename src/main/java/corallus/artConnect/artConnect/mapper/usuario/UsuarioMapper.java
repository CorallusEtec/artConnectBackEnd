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
    @Mapping(expression = "java(entity.getSeguidores().size())", target = "totalSeguidores")
    @Mapping(expression = "java(entity.getSeguido().size())", target = "totalSeguindo")
    @Mapping(target = "arte", ignore = true)
    @Mapping(target = "generosArte", ignore = true) 
    UsuarioResponse toDTO(Usuario entity);

    List<UsuarioResponse> toDTOList(List<Usuario> entityList);
}