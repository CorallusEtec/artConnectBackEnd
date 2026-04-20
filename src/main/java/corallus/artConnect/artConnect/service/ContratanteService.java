package corallus.artConnect.artConnect.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;

@Service
public class ContratanteService {
    @Autowired
    private ContratanteRepository contratanteRepository;

    public List<Contratante> findAll() {
        return this.contratanteRepository.findAll();
    }

    public Contratante findById(Long id) {
        return this.contratanteRepository.findById(id).orElse(null);
    }

  
    public String save(Contratante contratante) {
        this.contratanteRepository.save(contratante);
        return "Contratante cadastrado com sucesso!";
    }


    public String edit(Contratante contratante) {
        if (contratante.getId() == null || !this.contratanteRepository.existsById(contratante.getId())) {
            return "Erro: Contratante não encontrado para edição.";
        }
        
        this.contratanteRepository.save(contratante);
        return "Contratante atualizado com sucesso!";
    }
}