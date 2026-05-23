package corallus.artConnect.artConnect.dto.request.contato;

public record ContatoSaveRequest(
    Long idUsuario,
    Long idTipoContato,
    String valorContato
) {
    
}
