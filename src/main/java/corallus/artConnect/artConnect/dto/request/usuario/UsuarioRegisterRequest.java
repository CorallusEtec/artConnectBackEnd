package corallus.artConnect.artConnect.dto.request.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UsuarioRegisterRequest(
        MultipartFile fotoPerfil,
        @NotNull(message = "A requisição não pode vir vazia")
        UsuarioRegisterPrincipalRequest principal
) {}
