package corallus.artConnect.artConnect.dto.response.contato;

import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;

public record ContatoResponse(
    Long idContato,
    TipoContatoResponse tipoContato,
    String valorContato
) {}
