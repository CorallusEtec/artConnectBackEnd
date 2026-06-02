package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.entity.status.TipoStatus;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoStatusService {

    private final TipoStatusRepository tipoStatusRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public TipoStatusService(TipoStatusRepository tipoStatusRepository) {
        this.tipoStatusRepository = tipoStatusRepository;
    }

    /**
     * Função que encapsula a busca por um tipo de status no banco de dados
     *
     * @param eTipoStatus Valor de status baseado em
     * @return Retorna o tipo de status com o valor informado cadastrado no banco de dados.
     */

    public TipoStatus findByName(ETipoStatus eTipoStatus) {
        return this.tipoStatusRepository.findByNomeTipoStatus(eTipoStatus)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
