package corallus.artConnect.artConnect.dto;

import corallus.artConnect.artConnect.entity.Status;

public record UsuarioLoginDTO (
		
	Long id, String nome, Status status
		
) {}