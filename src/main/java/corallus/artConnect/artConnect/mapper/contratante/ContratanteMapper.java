package corallus.artConnect.artConnect.mapper.contratante;

import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratanteMapper extends BaseMapper<Contratante, ContratanteResponse> {}
