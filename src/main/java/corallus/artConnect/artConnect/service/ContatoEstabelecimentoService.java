package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.ContatoEstabelecimento;
import corallus.artConnect.artConnect.repository.ContatoEstabelecimentoRepository;

@Service
public class ContatoEstabelecimentoService {
    @Autowired
    private ContatoEstabelecimentoRepository contatoEstabelecimentoRepository;

    public List<ContatoEstabelecimento> findAll() {
        return contatoEstabelecimentoRepository.findAll();
    }

    //inserir contato do estabelecimento
    public String cadastrarContato(ContatoEstabelecimento contato) {
        contatoEstabelecimentoRepository.save(contato);
        return "Contato cadastrado";
    }



    //deletar contato de estabelecimento
    public String deletarContato(Long idContatoEstabelecimento) {
    	try {
    		this.contatoEstabelecimentoRepository.deleteById(idContatoEstabelecimento);
    		return "Contato deletado com sucesso!";
    	} catch (Exception e) {
			return e.getMessage();
		}
    }
    public List<ContatoEstabelecimento> findByIdParceiro(Long idParceiro) {
    		try {
    			return this.contatoEstabelecimentoRepository.findByIdEstabelecimento(idParceiro);
    		} catch (Exception e) {
				throw new RuntimeException();
			}
    }

    
}
