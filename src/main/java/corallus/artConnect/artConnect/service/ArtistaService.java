package corallus.artConnect.artConnect.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.atores.artista.ArtistaCadastroDTO;
import corallus.artConnect.artConnect.dto.atores.artista.ArtistaDTO;
import corallus.artConnect.artConnect.dto.atores.artista.ArtistaEditDTO;
import corallus.artConnect.artConnect.entity.ListaTipoStatus;
import corallus.artConnect.artConnect.entity.Seguida;


import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.TipoConta;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.TipoStatusRepository;
import corallus.artConnect.artConnect.repository.atores.ArtistaRepository;

@Service
public class ArtistaService implements IValidacoes {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private TipoStatusRepository tipoStatusRepository;

    public List<ArtistaDTO> findAll() {
        return this.artistaRepository.findAll().stream().map(ArtistaDTO::toDTO).toList();        
    }


    public ArtistaDTO findById(Long id) {
        Artista model = this.artistaRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException());

        return ArtistaDTO.toDTO(model);
    }
       
    
    public String save(ArtistaCadastroDTO artistaDTO) { 
        
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
        statusInicial.setTipoStatus(this.tipoStatusRepository.findByNomeTipoStatus(ListaTipoStatus.ATIVO.name()).get());
        statusInicial.setDataModificacao(LocalDateTime.now());
        

        // DATA, STATUS E TIPO DE CONTA
        artista.setDataCriacao(LocalDateTime.now());
        artista.setStatus(statusInicial);
        artista.setTipoConta(TipoConta.ARTISTA.name());


        artista.setStatus(statusInicial);
        
        this.artistaRepository.save(artista);
        return "Artista cadastrado com sucesso!";
    }

  
    public String edit(Long id, ArtistaEditDTO artistaDTO) {
        
        if (!this.artistaRepository.existsById(id)) {
            throw new UserNotFoundException("Não foi possivel editar: Conta não existente.");
        }

        // CAMPOS QUE NÃO PODEM FICAR VAZIOS OU NULOS
        validarString("O nome não pode ser vazio.", new String[] {artistaDTO.nome()});
        
        // BUSCA ARTISTA NO BANCO
        Artista artista = this.artistaRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException("Não foi possivel editar: Conta não existente."));
        
        artista.setId(id);
        artista.setNome(artistaDTO.nome());
        artista.setNomeArtistico(artistaDTO.nomeArtistico());
        artista.setDataNasc(artistaDTO.dataNasc());
        artista.setArte(artistaDTO.arte());

        // Logradouro
        artista.setNomeLog(artistaDTO.nomeLog());
        artista.setNumLog(artistaDTO.numLog());
        artista.setCep(artistaDTO.cep());
        artista.setBairro(artistaDTO.bairro());
        artista.setComplemento(artistaDTO.complemento());
        artista.setCidade(artistaDTO.cidade());
        artista.setUf(artistaDTO.uf());

        artista.setTextoBio(artistaDTO.textoBio());
        artista.setContatos(artistaDTO.contatos());



        this.artistaRepository.save(artista);
        return "Artista atualizado com sucesso!";
    }


    // USAR PARA VALIDAR CAMPOS STRING NAS REQUISIÇÕES
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