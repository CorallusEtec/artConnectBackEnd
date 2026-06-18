package corallus.artConnect.artConnect.mapper.contratante;

import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContratanteMapper {
    ContratanteResponse toDTO(Contratante entity);

    List<ContratanteResponse> toDTOList(List<Contratante> entityList);
}
