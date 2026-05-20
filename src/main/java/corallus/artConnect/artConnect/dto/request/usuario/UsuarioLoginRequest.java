package corallus.artConnect.artConnect.dto.request.usuario;

import corallus.artConnect.artConnect.entity.Status;

public record UsuarioLoginRequest (
		
	Long id, String nome, Status status
		
) {}