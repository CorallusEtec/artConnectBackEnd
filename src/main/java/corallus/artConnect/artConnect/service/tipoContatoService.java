package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.tipoContato;
import corallus.artConnect.artConnect.repository.tipoContatoRepository;

@Service
public class tipoContatoService {
	@Autowired
    private tipoContatoRepository tipoContatoRepository;
	
	//lista todos os tipos de contato cadastrados
    public List<tipoContato> findAll() {
        return tipoContatoRepository.findAll();
    }
    
    //cria um tipo de contato
    public String criarTipo(tipoContato tipoContato) {
    	try {
    		tipoContatoRepository.save(tipoContato);
    		return "Tipo de contato cadastrado com sucesso!";
    	} catch (Exception e) {
    		return "Erro ao cadastrar tipo de contato: "+e.getMessage();
    		}
    }
}
