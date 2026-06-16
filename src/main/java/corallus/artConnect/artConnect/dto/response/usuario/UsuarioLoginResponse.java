package corallus.artConnect.artConnect.dto.response.usuario;

import corallus.artConnect.artConnect.enumeration.ETipoConta;

public record UsuarioLoginResponse(
    Long id,
    String token,
    ETipoConta tipoConta
) {}
