package corallus.artConnect.artConnect.service;
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


    //deletar contato de admin
    public String deletarContato(Long idContatoAdmin) {
    	try {
    		this.contatoAdminRepository.deleteById(idContatoAdmin);
    		return "Contato deletado com sucesso!";
    	} catch (Exception e) {
			return e.getMessage();
		}
    }

}
