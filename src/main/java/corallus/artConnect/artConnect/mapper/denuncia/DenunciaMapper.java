package corallus.artConnect.artConnect.mapper.denuncia;

import corallus.artConnect.artConnect.dto.response.denuncia.DenunciaResponse;
import corallus.artConnect.artConnect.entity.Denuncia;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DenunciaMapper {
    DenunciaResponse toDTO(Denuncia entity);

    List<DenunciaResponse> toDTOList(List<Denuncia> entityList);
}
