package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.status.StatusRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service de status das outras entidades da aplicação
 *
 * @author SamuMeneDev
 */
@Service
public class StatusService {

    private final StatusRepository statusRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> findAll() {
        return this.statusRepository.findAll();
    }

    /**
     * Instancia e persiste um novo status, sendo o ativo o Status padrão
     *
     * @return Retorna um novo status com o tipo ATIVO
     */
    public Status generateStatus() {
        Status status = new Status();

        status.setTipoStatus(ETipoStatus.ATIVO);
        status.setDataModificacao(LocalDateTime.now());

        return this.statusRepository.save(status);
    }

    /**
     * Instancia e persiste um novo status com o tipo alterável.
     *
     * @param tipoStatus O tipo do status a ser criado, seguindo a enumeração {@link ETipoStatus}
     * @return Retorna o Status já persistido no banco
     */
    public Status generateStatus(ETipoStatus tipoStatus) {
        Status status = new Status();
        // SE tipoStatus FOR null; RETORNA O STATUS PADRÃO (ATIVO)
        if(tipoStatus == null) {tipoStatus = ETipoStatus.ATIVO;}

        status.setTipoStatus(tipoStatus);
        status.setDataModificacao(LocalDateTime.now());

        return this.statusRepository.save(status);
    }

    /**
     * Instancia e persiste um novo status com o tipo alterável e com uma descrição opcional.
     *
     * @param tipoStatus O tipo do status a ser criado, seguindo a enumeração {@link ETipoStatus}
     * @param descricao Descrição do status
     * @return Retorna o Status já persistido no banco
     */
    public Status generateStatus(ETipoStatus tipoStatus, String descricao) {
        Status status = new Status();

        // SE tipoStatus FOR null; RETORNA O STATUS PADRÃO (ATIVO)
        if(tipoStatus == null) {tipoStatus = ETipoStatus.ATIVO;}

        status.setTipoStatus(tipoStatus);
        status.setDataModificacao(LocalDateTime.now());
        status.setDescricao(descricao);

        return this.statusRepository.save(status);
    }

    public Status modifyStatus(Long idStatus, Status newStatus) {
        if(!this.statusRepository.existsById(idStatus)) {
            throw new ResourceNotFoundException("Status não encontrado");
        }
        newStatus.setDataModificacao(LocalDateTime.now());
        newStatus.setId(idStatus);
        return this.statusRepository.save(newStatus);
    }

}
