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

    //alterar contato de estabelecimento
    public String alterarContato(Long idContatoEstabelecimento, ContatoEstabelecimento contEstabelecimentoAlterado) {
    try {
        ContatoEstabelecimento contatoExistente = contatoEstabelecimentoRepository
            .findById(idContatoEstabelecimento)
            .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
        
        ContatoEstabelecimento contatoParaSalvar = new ContatoEstabelecimento();
        
        contatoParaSalvar.setIdContatoEstabelecimento(contatoExistente.getIdContatoEstabelecimento());
        contatoParaSalvar.setIdTipoContato(contEstabelecimentoAlterado.getIdTipoContato() != null ? contEstabelecimentoAlterado.getIdTipoContato() : contatoExistente.getIdTipoContato());
        contatoParaSalvar.setValorContatoEstabelecimento(contEstabelecimentoAlterado.getValorContatoEstabelecimento() != null ? contEstabelecimentoAlterado.getValorContatoEstabelecimento() : contatoExistente.getValorContatoEstabelecimento());
        contatoParaSalvar.setIdEstabelecimento(contEstabelecimentoAlterado.getIdEstabelecimento() != null ? contEstabelecimentoAlterado.getIdEstabelecimento() : contatoExistente.getIdEstabelecimento());
        contatoEstabelecimentoRepository.save(contatoParaSalvar);
        return "Contato alterado com sucesso!";
        
    } catch (Exception e) {
        return "Erro: " + e.getMessage();
    }
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

    //listar contato por id
    public List<ContatoEstabelecimento> findByIdEstabelecimento(Long idEstabelecimento) {
        return contatoEstabelecimentoRepository.findByIdEstabelecimento(idEstabelecimento);
    }
    
}
