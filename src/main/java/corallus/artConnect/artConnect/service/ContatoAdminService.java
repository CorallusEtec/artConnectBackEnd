package corallus.artConnect.artConnect.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.ContatoAdmin;
import corallus.artConnect.artConnect.repository.ContatoAdminRepository;

@Service
public class ContatoAdminService {
    @Autowired
    private ContatoAdminRepository contatoAdminRepository;

    //inserir contato do admin
    public String cadastrarContato(ContatoAdmin contato) {
        contatoAdminRepository.save(contato);
        return "Contato cadastrado";
    }

    //alterar contato de admin
    public String alterarContato(Long idContatoAdmin, ContatoAdmin contAdminAlterado) {
    try {
        ContatoAdmin contatoExistente = contatoAdminRepository
            .findById(idContatoAdmin)
            .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
        
        ContatoAdmin contatoParaSalvar = new ContatoAdmin();
        
        contatoParaSalvar.setIdContatoAdmin(contatoExistente.getIdContatoAdmin());
        contatoParaSalvar.setIdTipoContato(contAdminAlterado.getIdTipoContato() != null ? contAdminAlterado.getIdTipoContato() : contatoExistente.getIdTipoContato());
        contatoParaSalvar.setValorContatoAdmin(contAdminAlterado.getValorContatoAdmin() != null ? contAdminAlterado.getValorContatoAdmin() : contatoExistente.getValorContatoAdmin());
        contatoParaSalvar.setIdAdmin(contAdminAlterado.getIdAdmin() != null ? contAdminAlterado.getIdAdmin() : contatoExistente.getIdAdmin());
        
        this.contatoAdminRepository.save(contatoParaSalvar);
        return "Contato alterado com sucesso!";
        
    } catch (Exception e) {
        return e.getMessage();
    }
}

    //deletar contato de admin
    public String deletarContato(Long idContatoAdmin) {
    	try {
    		this.contatoAdminRepository.deleteById(idContatoAdmin);
    		return "Contato deletado com sucesso!";
    	} catch (Exception e) {
			return e.getMessage();
		}
    }

    //listar contato por id
    public List<ContatoAdmin> findByIdAdmin(Long idAdmin) {
        return contatoAdminRepository.findByIdAdmin(idAdmin);
    }
}
