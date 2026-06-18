package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.mapper.contratante.ContratanteMapper;
import corallus.artConnect.artConnect.queryFilter.ContratanteFindAllQF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ContratanteResponse> findAll(ContratanteFindAllQF find, Pageable pageable) {
        Page<Contratante> lista = this.contratanteRepository.findAll(find.toSpecification(), pageable);
        return lista.map(contratanteMapper::toDTO);
    }

    public ContratanteResponse findById(Long id) {
        Contratante contratante = this.contratanteRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return this.contratanteMapper.toDTO(contratante);
    }

    public MessageApiResponse edit(Long id, ContratanteEditRequest editRequest) {
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
        return new MessageApiResponse("Contratante atualizado com sucesso!");
    }
}