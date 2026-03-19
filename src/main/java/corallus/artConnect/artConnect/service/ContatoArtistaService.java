package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import corallus.artConnect.artConnect.entity.ContatoArtista;
import corallus.artConnect.artConnect.repository.ContatoArtistaRepository;

@Service
public class ContatoArtistaService {
    @Autowired
    private ContatoArtistaRepository contatoArtistaRepository;
    
    //listar todos os contatos de todos os artistas
    public List<ContatoArtista> findAll() {
        return contatoArtistaRepository.findAll();
    }

    //inserir contato do artista
    public String cadastrarContato(ContatoArtista contato) {
        contatoArtistaRepository.save(contato);
        return "Contato cadastrado";
    }

    //alterar contato de artista
    public String alterarContato(Long idContatoArtista, ContatoArtista contArtistaAlterado) {
    try {
        ContatoArtista contatoExistente = contatoArtistaRepository
            .findById(idContatoArtista)
            .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
        
        ContatoArtista contatoParaSalvar = new ContatoArtista();
        
        contatoParaSalvar.setIdContatoArtista(contatoExistente.getIdContatoArtista());
        contatoParaSalvar.setIdTipoContato(contArtistaAlterado.getIdTipoContato() != null ? contArtistaAlterado.getIdTipoContato() : contatoExistente.getIdTipoContato());
        contatoParaSalvar.setValorContatoArtista(contArtistaAlterado.getValorContatoArtista() != null ? contArtistaAlterado.getValorContatoArtista() : contatoExistente.getValorContatoArtista());
        contatoParaSalvar.setIdArtista(contArtistaAlterado.getIdArtista() != null ? contArtistaAlterado.getIdArtista() : contatoExistente.getIdArtista());
        
        this.contatoArtistaRepository.save(contatoParaSalvar);
        return "Contato alterado com sucesso!";
        
    } catch (Exception e) {
        return e.getMessage();
    }
}

    //deletar contato de artista
    public String deletarContato(Long idContatoArtista) {
    	try {
    		this.contatoArtistaRepository.deleteById(idContatoArtista);
    		return "Contato deletado com sucesso!";
    	} catch (Exception e) {
			return e.getMessage();
		}
    }

    //listar contato por id
    public List<ContatoArtista> findByIdArtista(Long idArtista) {
        return contatoArtistaRepository.findByIdArtista(idArtista);
    }
}
