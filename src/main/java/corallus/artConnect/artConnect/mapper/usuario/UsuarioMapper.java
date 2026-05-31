package corallus.artConnect.artConnect.mapper.usuario;

import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import corallus.artConnect.artConnect.mapper.contato.ContatoMapper;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = {ContatoMapper.class}
)
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioResponse> {}
