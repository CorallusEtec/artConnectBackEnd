package corallus.artConnect.artConnect.service;

import java.util.ArrayList;
import corallus.artConnect.artConnect.dto.request.arte.ArteEditRequest;
import corallus.artConnect.artConnect.dto.request.arte.ArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.error.errors.ArteAlreadyExistsException;
import corallus.artConnect.artConnect.repository.arte.ArteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.error.errors.ArteNotFoundException;

@Service
public class ArteService {

    private final ArteRepository arteRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ArteService(ArteRepository arteRepository) {
        this.arteRepository = arteRepository;
    }

    // MÉTODOS LÓGICOS

    public Page<Arte> findAll(Pageable pageable) {
        return this.arteRepository.findAll(pageable);
    } 

    public Arte findById(Long id) {
        return this.arteRepository.findById(id)
                .orElseThrow(ArteNotFoundException::new);
    }

    public MessageApiResponse save(ArteSaveRequest saveRequest) {
        if(this.arteRepository.existsByNomeArte(saveRequest.nomeArte())) {
            throw new ArteAlreadyExistsException();
        }

        Arte arte = new Arte();
        arte.setNomeArte(saveRequest.nomeArte());
        arte.setUsuarios(new ArrayList<>());

        this.arteRepository.save(arte);
        return new MessageApiResponse("Arte criada.");
    }

    public MessageApiResponse edit(Long id, ArteEditRequest editRequest) {
        Arte arte = this.arteRepository.findById(id)
                .orElseThrow(ArteNotFoundException::new);

        arte.setId(id);
        arte.setNomeArte(editRequest.nomeArte());

        this.arteRepository.save(arte);
        return new MessageApiResponse("Arte alterada com sucesso.");
    }

    public MessageApiResponse delete(Long id) {
        if(!this.arteRepository.existsById(id)) {
            throw new ArteNotFoundException();
        }

        this.arteRepository.deleteById(id);
        return new MessageApiResponse("Arte deletada com sucesso.");
    }
}
