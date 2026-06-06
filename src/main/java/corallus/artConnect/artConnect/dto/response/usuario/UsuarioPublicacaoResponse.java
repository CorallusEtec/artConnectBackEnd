package corallus.artConnect.artConnect.dto.response.usuario;

import corallus.artConnect.artConnect.enumeration.ETipoConta;

public record UsuarioPublicacaoResponse(
        Long id,
        String nome,
        ETipoConta tipoConta
) {}
