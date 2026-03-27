package corallus.artConnect.artConnect.dto;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.contato.ContatoArtista;

public record ArtistaEditarDTO(
	
	String nome,
	String nomeArtistico,
	LocalDate dataNasc,
	Arte arte,
	Character sexo,
	
	//	LOGRADOURO / ENDEREÇO
	
	String nomeLog,
	Short numLog,
	String cep,
	String bairro,
	String complemento,
	String cidade,
	String uf,
	
	List<ContatoArtista> contatos
	    
) {}
