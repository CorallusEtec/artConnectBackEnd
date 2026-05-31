package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import corallus.artConnect.artConnect.dto.response.MessageResponse;
import corallus.artConnect.artConnect.queryFilter.ContratanteFindAllQF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.request.contratante.ContratanteCadastroRequest;
import corallus.artConnect.artConnect.dto.request.contratante.ContratanteEditRequest;
import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.entity.status.Status;
import corallus.artConnect.artConnect.enums.ListaTipoConta;
import corallus.artConnect.artConnect.enums.ListaTipoStatus;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.status.StatusRepository;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;

@Service
public class ContratanteService implements IValidacoes {
    @Autowired
    private ContratanteRepository contratanteRepository;

    @Autowired
    private TipoStatusRepository tipoStatusRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private StatusRepository statusRepository;

    public List<ContratanteResponse> findAll(ContratanteFindAllQF find) {
        return this.contratanteRepository.findAll(find.toSpecification())
                .stream()
                .map(ContratanteResponse::toDTO)
                .collect(Collectors.toList());
    }

    public ContratanteResponse findById(Long id) {
        Contratante contratante = this.contratanteRepository.findById(id).orElseThrow(()->new UserNotFoundException());
        return ContratanteResponse.toDTO(contratante);
    }

  
    public MessageResponse save(String tipo, ContratanteCadastroRequest contratanteDTO) {
        
        // Valida campos que nunca podem ser vazios
        validarString(null, new String[] {contratanteDTO.nome(), contratanteDTO.email(), contratanteDTO.senha()});

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
        Status statusInicial = new Status();

        // SE FOR EMPRESA
        if(tipo.equalsIgnoreCase("cnpj")) {
            // VALIDAÇÕES ESPECIFICAS DE EMPRESA
            validarString(null, new String[] {contratanteDTO.razaoSocial(), contratanteDTO.cnpj()});
            
            // SALVA DADOS ESPECIFICOS DE EMPRESA
            contratante.setRazaoSocial(contratanteDTO.razaoSocial());
            contratante.setCnpj(contratanteDTO.cnpj());
            contratante.setTipoConta(ListaTipoConta.CONTRATANTE_CNPJ.name());

            // EMPRESAS DEVEM SER APROVADAS PELO ADMIN: STATUS PENDENTE
            statusInicial.setTipoStatus(this.tipoStatusRepository.findByNomeTipoStatus(ListaTipoStatus.PENDENTE.name()).get());
            statusInicial.setDescricao("Esperando aprovação do administrador");
           
        } else {
            contratante.setTipoConta(ListaTipoConta.CONTRATANTE_CPF.name());
            statusInicial.setTipoStatus(this.tipoStatusRepository.findByNomeTipoStatus(ListaTipoStatus.ATIVO.name()).get());
            statusInicial.setDescricao(null);
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

        // DATA DO STATUS E PERSISTENCIA
        statusInicial.setDataModificacao(LocalDateTime.now());

        this.statusRepository.save(statusInicial);

        // DATA DE CRIAÇÃO E SET DO STATUS
        contratante.setDataCriacao(LocalDateTime.now());
        contratante.setStatus(statusInicial);
        

        this.contratanteRepository.save(contratante);
        return new MessageResponse("Contratante cadastrado com sucesso!");
    }


    public MessageResponse edit(Long id, ContratanteEditRequest contratanteDTO) {

        // CAMPOS QUE NÃO PODEM ESTAR VAZIO
        validarString(null, new String[] { contratanteDTO.nome() });
        
        Contratante contratante = this.contratanteRepository.findById(id)
        // SE NÃO EXISTIR
        .orElseThrow(()->new UserNotFoundException());
        contratante.setNome(contratanteDTO.nome());
        contratante.setRazaoSocial(contratanteDTO.razaoSocial());
       
        // LOGRADOURO
        contratante.setNomeLog(contratanteDTO.nomeLog());
        contratante.setNumLog(contratanteDTO.numLog());
        contratante.setCep(contratanteDTO.cep());
        contratante.setBairro(contratanteDTO.bairro());
        contratante.setComplemento(contratanteDTO.complemento());
        contratante.setCidade(contratanteDTO.cidade());
        contratante.setUf(contratanteDTO.uf());

        // OUTROS DADOS
        contratante.setTextoBio(contratanteDTO.textoBio());
        contratante.setDataNasc(contratanteDTO.dataNasc());
        contratante.setSexo(contratanteDTO.sexo());



        this.contratanteRepository.save(contratante);
        return new MessageResponse("Contratante atualizado com sucesso!");
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