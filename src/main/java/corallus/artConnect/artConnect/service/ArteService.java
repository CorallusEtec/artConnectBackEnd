package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.error.errors.ArteNotFoundException;
import corallus.artConnect.artConnect.repository.ArteRepository;

@Service
public class ArteService implements IValidacoes {
    @Autowired
    private ArteRepository arteRepository;
    
    public List<Arte> findAll() {
        List<Arte> lista = this.arteRepository.findAll();

        return lista;
    } 

    public Arte findById(Long id) {
        if(!this.arteRepository.existsById(id)) {
            throw new ArteNotFoundException();
        }

        Arte arte = this.arteRepository.findById(id).get();

        return arte;
    }

    public String save(Arte arte) {
        arte.setId(null);

        validarString(null, new String[] { arte.getNomeArte() });

        this.arteRepository.save(arte);
        return "Arte criada.";
    }

    public String edit(Long id, Arte arte) {
        if(!this.arteRepository.existsById(id)) {
            throw new ArteNotFoundException();
        }
        
        validarString(null, new String[] { arte.getNomeArte() });

        arte.setId(id);

        this.arteRepository.save(arte);

        return "Arte alterada com sucesso.";
    }

    public String delete(Long id) {
        if(!this.arteRepository.existsById(id)) {
            throw new ArteNotFoundException();
        }

        this.arteRepository.deleteById(id);

        return "Arte deletada com sucesso.";
    }

    @Override
    public void validarString(String msgErro, String[] campos) {
        for (String texto : campos) {
            if(texto.equals(null) || texto.trim().isEmpty()) {
                if(msgErro == null) {
                    throw new IllegalArgumentException("Há campos vazios na requisição.");
                } else {
                    throw new IllegalArgumentException(msgErro);
                }
            }
        }   
    }

    
}
