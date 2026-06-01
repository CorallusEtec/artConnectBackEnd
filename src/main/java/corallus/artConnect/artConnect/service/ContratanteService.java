package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.mapper.contratante.ContratanteMapper;
import corallus.artConnect.artConnect.queryFilter.ContratanteFindAllQF;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.contratante.ContratanteCadastroRequest;
import corallus.artConnect.artConnect.dto.request.contratante.ContratanteEditRequest;
import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;

@Service
public class ContratanteService {

    private final ContratanteRepository contratanteRepository;

    private final UsuarioRepository usuarioRepository;

    private final StatusService statusService;

    private final ContratanteMapper contratanteMapper;

    // INJEÇÃO DE DEPENDÊNCIA


    public ContratanteService(ContratanteRepository contratanteRepository,
                              UsuarioRepository usuarioRepository,
                              StatusService statusService,
                              ContratanteMapper contratanteMapper) {
        this.contratanteRepository = contratanteRepository;
        this.usuarioRepository = usuarioRepository;
        this.statusService = statusService;
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

    public MessageResponse save(String tipo, ContratanteCadastroRequest contratanteDTO) {
        // VALIDA CNPJ E EMAIL UNICOS
        // Se já existir e-mail
        if(this.usuarioRepository.existsByEmail(contratanteDTO.email())) {
            throw new UserAlreadyExistsException();
        }
        // Se for empresa
        if(!tipo.equalsIgnoreCase("cpf")) {
            // Validação de CNPJ
            if(this.contratanteRepository.existsByCnpj(contratanteDTO.cnpj())) {
                throw new UserAlreadyExistsException("Não foi possível cadastrar: CNPJ já existente.");
            }
        }
        
        Contratante contratante = new Contratante();

        // SE FOR EMPRESA
        if(tipo.equalsIgnoreCase("cnpj")) {
            // SALVA DADOS ESPECIFICOS DE EMPRESA
            contratante.setRazaoSocial(contratanteDTO.razaoSocial());
            contratante.setCnpj(contratanteDTO.cnpj());
            contratante.setTipoConta(ETipoConta.CONTRATANTE_CNPJ);

            // EMPRESAS DEVEM SER APROVADAS PELO ADMIN: STATUS PENDENTE
            contratante.setStatus(this.statusService.generateStatus(ETipoStatus.PENDENTE, "Esperando aprovação"));
        } else {
            contratante.setTipoConta(ETipoConta.CONTRATANTE_CPF);
            contratante.setStatus(this.statusService.generateStatus());
        }

        // DADOS GERAIS SALVOS
        contratante.setNome(contratanteDTO.nome());
        contratante.setEmail(contratanteDTO.email());
        contratante.setSenha(contratanteDTO.senha());

        // LISTAS INCIALIZADAS
        contratante.setSeguidores(new HashSet<>());
        contratante.setSeguido(new HashSet<>());
        contratante.setContatos(new ArrayList<>());
        contratante.setPublicacoes(new ArrayList<>());
        contratante.setReacoes(new HashSet<>());

        // DATA DE CRIAÇÃO E SET DO STATUS
        contratante.setDataCriacao(LocalDateTime.now());

        this.contratanteRepository.save(contratante);
        return new MessageResponse("Contratante cadastrado com sucesso!");
    }

    public MessageResponse edit(Long id, ContratanteEditRequest editRequest) {
        Contratante contratante = this.contratanteRepository.findById(id)
        // SE NÃO EXISTIR
        .orElseThrow(UserNotFoundException::new);
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