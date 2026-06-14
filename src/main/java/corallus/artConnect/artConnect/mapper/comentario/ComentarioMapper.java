package corallus.artConnect.artConnect.mapper.comentario;

import corallus.artConnect.artConnect.dto.response.comentario.ComentarioResponse;
import corallus.artConnect.artConnect.entity.Comentario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import corallus.artConnect.artConnect.entity.Reacao;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {

    /**
     * Mapeamento da entidade comentário para DTO
     * Os mapeamentos com expressões abaixo são feitas para retornar a reação do usuário autenticado e
     * a quantidade de likes (reacão no comentário)
     *
     * @param entity Entidade {@link Comentario}
     * @return DTO de resposta de comentário
     */
    @Mapping(target = "likes", expression = "java(entity.getReacoes().stream()" +
            ".map(l->l.getTipoReacao()==corallus.artConnect.artConnect.enumeration.ETipoReacao.LIKE)" +
            ".count())")
    @Mapping(target = "reacaoUsuario", expression = "java(entity.getReacoes().stream()" +
            ".filter(r->r.getUsuario().getId()" +
            ".equals(usuarioId))" +
            ".map(corallus.artConnect.artConnect.entity.Reacao::getTipoReacao)" +
            ".findFirst().orElse(null))")
    ComentarioResponse toDTO(Comentario entity, Long usuarioId);

    List<ComentarioResponse> toDTOList(List<Comentario> entityList);
}
