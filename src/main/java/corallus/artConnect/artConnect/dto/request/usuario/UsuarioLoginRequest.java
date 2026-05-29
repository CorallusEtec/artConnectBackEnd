package corallus.artConnect.artConnect.dto.request.usuario;

import corallus.artConnect.artConnect.entity.status.Status;
/**
 * ESSE RECORD SERÁ DEPRECIADO NA PROXIMA VERSÃO
 */
public record UsuarioLoginRequest (
		
	Long id, String nome, Status status
		
) {}