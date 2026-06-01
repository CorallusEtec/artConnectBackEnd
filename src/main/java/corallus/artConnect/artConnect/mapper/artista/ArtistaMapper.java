package corallus.artConnect.artConnect.mapper.artista;

import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistaMapper extends BaseMapper<Artista, ArtistaResponse> {
}
