package corallus.artConnect.artConnect.mapper;

import corallus.artConnect.artConnect.dto.response.MensagemResponse;
import corallus.artConnect.artConnect.entity.Mensagem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MensagemMapper {
    MensagemResponse toDTO(Mensagem entity);

    List<MensagemResponse> toDTOList(List<Mensagem> entityList);
}
