package corallus.artConnect.artConnect.mapper.artista;

import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.entity.atores.Artista;import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistaMapper {
    ArtistaResponse toDTO(Artista entity);

    List<ArtistaResponse> toDTOList(List<Artista> entityList);
}
