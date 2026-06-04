package corallus.artConnect.artConnect.service;

import java.util.List;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.mapper.contratante.ContratanteMapper;
import corallus.artConnect.artConnect.queryFilter.ContratanteFindAllQF;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.contratante.ContratanteEditRequest;
import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;

@Service
public class ContratanteService {

    private final ContratanteRepository contratanteRepository;
    private final ContratanteMapper contratanteMapper;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContratanteService(
            ContratanteRepository contratanteRepository,
            ContratanteMapper contratanteMapper
    ) {
        this.contratanteRepository = contratanteRepository;
        this.contratanteMapper = contratanteMapper;
    }

    public List<ContratanteResponse> findAll(ContratanteFindAllQF find) {
        List<Contratante> lista = this.contratanteRepository.findAll(find.toSpecification());
        return this.contratanteMapper.toDTOList(lista);
    }

    public ContratanteResponse findById(Long id) {
        Contratante contratante = this.contratanteRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return this.contratanteMapper.toDTO(contratante);
    }

    public MessageResponse edit(Long id, ContratanteEditRequest editRequest) {
        // VERIFICA SE NÃO EXISTIR
        Contratante contratante = this.contratanteRepository.findById(id)
        .orElseThrow(NotAuthorizedException::new);

        contratante.setNome(editRequest.nome());
        contratante.setRazaoSocial(editRequest.razaoSocial());
       
        // LOGRADOURO
        UsuarioService.fillCommonEdits(contratante, editRequest);

        // OUTROS DADOS
        contratante.setDataNasc(editRequest.dataNasc());
        contratante.setSexo(editRequest.sexo());

        this.contratanteRepository.save(contratante);
        return new MessageResponse("Contratante atualizado com sucesso!");
    }
}