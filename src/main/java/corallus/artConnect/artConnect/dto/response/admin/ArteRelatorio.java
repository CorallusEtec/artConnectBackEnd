package corallus.artConnect.artConnect.dto.response.admin;

import corallus.artConnect.artConnect.entity.arte.Arte;

public record ArteRelatorio(
        Long quantidadeArtistas,
        Arte arte
) {
}
