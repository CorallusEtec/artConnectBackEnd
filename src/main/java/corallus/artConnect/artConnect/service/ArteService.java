package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.repository.ArteRepository;

@Service
public class ArteService {
    @Autowired
    private ArteRepository arteRepository;
    public List<Arte> findAll() {
        return arteRepository.findAll();
    }

    public String alterarArte(Long idArte, Arte arteAlterada) {
    	try {
    		arteAlterada.setId(idArte);
    		this.arteRepository.save(arteAlterada);
    		return "Arte alterada com sucesso!";
    	} catch (Exception e) {
			return e.getMessage();
		}
    }
    public String inserirArte(Arte arte) {
    	try {
    		this.arteRepository.save(arte);
    		return "Cadastro de arte realizado com sucesso!";
    	} catch (Exception e) {
    		e.printStackTrace();
    		return "Erro: "+e.getMessage();
    	}
    }
    public Arte findById(Long id) {
        return arteRepository.findById(id).orElse(null);
    }
    public String deletarArte(Long idArte) {
    	try {
    		this.arteRepository.deleteById(idArte);
    		return "Arte deletada com sucesso!";
    	} catch (Exception e) {
			return e.getMessage();
		}
    }
}
