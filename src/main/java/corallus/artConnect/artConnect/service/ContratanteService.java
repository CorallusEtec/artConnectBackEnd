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
        List<Contratante> lista = this.contratanteRepository.findAll();
       
        return lista;
    }

    public String save(Contratante contratante) {
        this.contratanteRepository.save(contratante);
        return "Contratante cadastrado com sucesso!";
    }

	public Contratante findById(Long id) {
		// TODO Auto-generated method stub
		return this.contratanteRepository.findById(id).orElse(null);
	}
}
