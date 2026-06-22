package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.arte.GeneroArteEditRequest;
import corallus.artConnect.artConnect.dto.request.arte.GeneroArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import corallus.artConnect.artConnect.error.errors.ArteNotFoundException;
import corallus.artConnect.artConnect.error.errors.GeneroArteAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.arte.ArteRepository;
import corallus.artConnect.artConnect.repository.arte.GeneroArteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroArteService {

    private final GeneroArteRepository generoArteRepository;
    private final ArteRepository arteRepository;

    public GeneroArteService(GeneroArteRepository generoArteRepository, ArteRepository arteRepository) {
        this.generoArteRepository = generoArteRepository;
        this.arteRepository = arteRepository;
    }

    public List<GeneroArte> findAll() {
        return this.generoArteRepository.findAll();
    }

    public List<GeneroArte> findByArteId(Long idArte) {
        return this.generoArteRepository.findAllByArte_Id(idArte);
    }

    public MessageApiResponse save(GeneroArteSaveRequest saveRequest) {
        if(this.generoArteRepository.existsByNomeGeneroArte(saveRequest.nomeGeneroArte())) {
            throw new GeneroArteAlreadyExistsException();
        }

        Arte arte = this.arteRepository.findById(saveRequest.arteId())
        .orElseThrow(ArteNotFoundException::new);

        GeneroArte generoArte = new GeneroArte();
        generoArte.setNomeGeneroArte(saveRequest.nomeGeneroArte());
        generoArte.setArte(arte);

        this.generoArteRepository.save(generoArte);
        return new MessageApiResponse("Gênero de arte criado.");
    }

    public MessageApiResponse deleteById(Long id) {
        if(generoArteRepository.existsById(id)) {
            this.generoArteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Genero de arte não encontradp");
        }
        return new MessageApiResponse("Genero deletado com sucesso");
    }

    public MessageApiResponse editById(GeneroArteEditRequest editRequest, Long id) {

        GeneroArte generoArte = this.generoArteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Genero de arte não encontrado"));


        generoArte.setNomeGeneroArte(editRequest.nomeGeneroArte());
        this.generoArteRepository.save(generoArte);

        return new MessageApiResponse("Genero de arte alterado com sucesso");
    }

    public GeneroArte findById(Long id) {
        return this.generoArteRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Genero de arte não encontrado"));
    }
}