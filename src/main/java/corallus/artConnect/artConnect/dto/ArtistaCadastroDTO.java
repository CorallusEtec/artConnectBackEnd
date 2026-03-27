package corallus.artConnect.artConnect.dto;

import java.time.LocalDate;
import java.util.List;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.contato.ContatoArtista;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;

public record ArtistaCadastroDTO (
	String nome,
	String nomeArtistico,
	String email,
	String senha,	
	LocalDate dataNasc,
	String cpf,
	Character sexo,
	Arte arte,
	
	// LOGRADOURO / ENDEREÇO
	String nomeLog,
	Short numLog,
	String cep,
	String bairro,
	String complemento,
	String cidade,
	String uf,
	
	List<ContatoArtista> contatos,
	
	List<Publicacao> publicacoes
) {}
