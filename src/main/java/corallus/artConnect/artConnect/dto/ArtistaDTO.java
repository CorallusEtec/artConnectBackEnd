package corallus.artConnect.artConnect.dto;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.contato.ContatoArtista;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public record ArtistaDTO(
		Long id,
		String nome,
		String email,
		String nomeArtistico,
		LocalDate dataNasc,
		Character sexo,
		String statusConta,
		String tipoConta,
		// LOGRADOURO / ENDEREÇO
		
		String nomeLog,
		Short numLog,
		String cep,
		String bairro,
		String complemento,
		String cidade,
		String uf,
		
		Arte arte,
		List<ContatoArtista> contatos,
		List<Publicacao> publicacoes
) {}
