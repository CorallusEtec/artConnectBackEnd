package corallus.artConnect.artConnect.dto.request.publicacao;

import org.springframework.web.multipart.MultipartFile;

public record PublicacaoSaveRequest(
        String legenda,
        String tipoMidia,
        MultipartFile arquivo
) {}
