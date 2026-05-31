package corallus.artConnect.artConnect.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.queryFilter.ArtistaFindAllQF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.request.artista.ArtistaCadastroRequest;
import corallus.artConnect.artConnect.dto.request.artista.ArtistaEditRequest;
import corallus.artConnect.artConnect.dto.response.artista.ArtistaResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.entity.status.Status;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.ArteNotFoundException;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.error.errors.UserAlreadyExistsException;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.ArteRepository;
import corallus.artConnect.artConnect.repository.TagRepository;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.status.StatusRepository;
import corallus.artConnect.artConnect.repository.status.TipoStatusRepository;

@Service
public class ArtistaService implements IValidacoes {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private TipoStatusRepository tipoStatusRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ArteRepository arteRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<ArtistaResponse> findAll(ArtistaFindAllQF filter) {
        List<ArtistaResponse> lista = this.artistaRepository.findAll(filter.toSpecification())
                .stream()
        .map(ArtistaResponse::toDTO)
        .collect(Collectors.toList());
        
        return lista;    
    }


    public ArtistaResponse findById(Long id) {
        Artista model = this.artistaRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException());

        return ArtistaResponse.toDTO(model);
    }
       
    
    public MessageResponse save(ArtistaCadastroRequest artistaDTO) {
        
        if(this.usuarioRepository.existsByEmail(artistaDTO.email())) {
            throw new UserAlreadyExistsException();
        }


        validarString(null, new String[] {artistaDTO.nome(), artistaDTO.email(), artistaDTO.senha()});
        Artista artista = new Artista();
        artista.setNome(artistaDTO.nome());
        artista.setEmail(artistaDTO.email());
        artista.setSenha(artistaDTO.senha());

        // Listas vazias por padrão no cadastro

        artista.setReacoes(new HashSet<Reacao>());
        artista.setSeguidores(new HashSet<Seguida>());
        artista.setSeguido(new HashSet<Seguida>());
        artista.setContatos(new ArrayList<Contato>());
        artista.setPublicacoes(new ArrayList<Publicacao>());



        // STATUS PADRÃO DE CRIAÇÂO: ATIVO
        Status statusInicial = new Status();
        statusInicial.setTipoStatus(this.tipoStatusRepository.findByNomeTipoStatus(ETipoStatus.ATIVO.name()).get());
        statusInicial.setDataModificacao(LocalDateTime.now());

        this.statusRepository.save(statusInicial);
        

        // DATA, STATUS E TIPO DE CONTA
        artista.setDataCriacao(LocalDateTime.now());
        artista.setStatus(statusInicial);
        artista.setTipoConta(ETipoConta.ARTISTA.name());


        artista.setStatus(statusInicial);
        
        this.artistaRepository.save(artista);
        return new MessageResponse("Artista cadastrado com sucesso!");
    }

  
    public MessageResponse edit(Long id, ArtistaEditRequest artistaDTO) {

        // CAMPOS QUE NÃO PODEM FICAR VAZIOS OU NULOS
        validarString("O nome não pode ser vazio.", new String[] {artistaDTO.nome()});
        
        // BUSCA ARTISTA NO BANCO
        Artista artista = this.artistaRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException("Não foi possivel editar: Conta não existente."));
        
        artista.setNome(artistaDTO.nome());
        artista.setNomeArtistico(artistaDTO.nomeArtistico());
        artista.setDataNasc(artistaDTO.dataNasc());
        
        if(artistaDTO.arte() != null) {
            if(this.arteRepository.existsById(artistaDTO.arte().getId())) {
                artista.setArte(this.arteRepository.findById(artistaDTO.arte().getId()).orElseThrow(()->new ArteNotFoundException()));
            } else {artista.setArte(null);}
        }
        


        // Logradouro
        artista.setNomeLog(artistaDTO.nomeLog());
        artista.setNumLog(artistaDTO.numLog());
        artista.setCep(artistaDTO.cep());
        artista.setBairro(artistaDTO.bairro());
        artista.setComplemento(artistaDTO.complemento());
        artista.setCidade(artistaDTO.cidade());
        artista.setUf(artistaDTO.uf());

        
        if(artistaDTO.listaTags() != null) {
            artista.setListaTags(
                artistaDTO.listaTags().stream()
                .map(t->this.tagRepository.findById(t.getId()).orElseThrow(()->new ResourceNotFoundException("Tag não existente")))
                .collect(Collectors.toList())
            );
        } else {
            artista.setListaTags(null);
        }
        


        artista.setTextoBio(artistaDTO.textoBio());

        this.artistaRepository.save(artista);
        return new MessageResponse("Artista atualizado com sucesso!");
    }


    // USAR PARA VALIDAR CAMPOS STRING NAS REQUISIÇÕES
    @Override
    public void validarString(String msgErro, String[] campos) {
        for (String texto : campos) {
            if(texto == (null) || texto.trim().isEmpty()) {
                if(msgErro == null) {
                    throw new IllegalArgumentException("Há campos vazios na requisição.");
                } else {
                    throw new IllegalArgumentException(msgErro);
                }
            }
        }
    }
}