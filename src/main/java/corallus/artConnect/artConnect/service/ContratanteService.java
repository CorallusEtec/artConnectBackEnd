package corallus.artConnect.artConnect.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.atores.contratante.ContratanteCadastroDTO;
import corallus.artConnect.artConnect.dto.atores.contratante.ContratanteDTO;
import corallus.artConnect.artConnect.dto.atores.contratante.ContratanteEditDTO;
import corallus.artConnect.artConnect.entity.ListaTipoStatus;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.TipoConta;
import corallus.artConnect.artConnect.entity.atores.Contratante;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.StatusRepository;
import corallus.artConnect.artConnect.repository.TipoStatusRepository;
import corallus.artConnect.artConnect.repository.atores.ContratanteRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;

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

    public List<ContratanteDTO> findAll() {
        return this.contratanteRepository.findAll().stream().map(ContratanteDTO::toDTO).toList();
    }

    public ContratanteDTO findById(Long id) {
        Contratante contratante = this.contratanteRepository.findById(id).orElseThrow(()->new UserNotFoundException());
        return ContratanteDTO.toDTO(contratante);
    }

  
    public String save(String tipo, ContratanteCadastroDTO contratanteDTO) {
        // VALIDA CNPJ E EMAIL UNICOS
        if(this.usuarioRepository.existsByEmail(contratanteDTO.email())) {
            throw new UserAlreadyExistsException();
        } else if(this.contratanteRepository.existsByCnpj(contratanteDTO.cnpj())) {
            throw new UserAlreadyExistsException("Não foi possível cadastrar: CNPJ já existente.");
        }

        validarString(null, new String[] {contratanteDTO.nome(), contratanteDTO.email(), contratanteDTO.senha()});
        
        Contratante contratante = new Contratante();
        Status statusInicial = new Status();

        // SE FOR EMPRESA
        if(tipo.equalsIgnoreCase("cnpj")) {
            // VALIDAÇÕES ESPECIFICAS DE EMPRESA
            validarString(null, new String[] {contratanteDTO.razaoSocial(), contratanteDTO.cnpj()});
            
            // SALVA DADOS ESPECIFICOS DE EMPRESA
            contratante.setRazaoSocial(contratanteDTO.razaoSocial());
            contratante.setCnpj(contratanteDTO.cnpj());
            contratante.setTipoConta(TipoConta.CONTRATANTE_CNPJ.name());

            // EMPRESAS DEVEM SER APROVADAS PELO ADMIN: STATUS PENDENTE
            statusInicial.setTipoStatus(this.tipoStatusRepository.findByNomeTipoStatus(ListaTipoStatus.PENDENTE.name()).get());
            statusInicial.setDescricao("Esperando aprovação do administrador");
           
        } else {
            contratante.setTipoConta(TipoConta.CONTRATANTE_CPF.name());
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
        return "Contratante cadastrado com sucesso!";
    }


    public String edit(Long id, ContratanteEditDTO contratanteDTO) {
        
        // SE NÃO EXISTIR
        if (!this.contratanteRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        validarString(null, new String[] { contratanteDTO.nome() });
        
        Contratante contratante = new Contratante();
        contratante.setId(id);
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
        contratante.setContatos(contratanteDTO.contatos());



        this.contratanteRepository.save(contratante);
        return "Contratante atualizado com sucesso!";
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