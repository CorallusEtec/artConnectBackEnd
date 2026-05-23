package corallus.artConnect.artConnect.dto.response.artista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import corallus.artConnect.artConnect.dto.response.contato.ContatoResponse;
import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Tag;
import corallus.artConnect.artConnect.entity.atores.Artista;
import corallus.artConnect.artConnect.entity.status.Status;

public record ArtistaResponse(
    // USUARIO
    Long id,
    String nome,
    String email,
    String tipoConta,
    Status status,
    LocalDateTime dataCriacao,
    
    // ARTISTA
    String nomeArtistico,
    List<Tag> listaTags,
    Arte arte,
    LocalDate dataNasc,
    
    // LOGRADOURO
    String nomeLog,
    Short numLog,
    String cep,
    String bairro,
    String complemento,
    String cidade,
    String uf,

    String textoBio,
    Set<Seguida> seguidores,
    Set<Seguida> seguido,
    List<ContatoResponse> contatos
    
    /**
     * 
     * SEM PUBLICACOES E REACOES POR MOTIVOS DE ABSTRAÇÃO
     * 
     */
) {
    public static class builder {
        // USUARIO
        private Long id;
        private String nome;
        private String email;
        private String tipoConta;
        private Status status;
        private LocalDateTime dataCriacao;

        // ARTISTA
        private String nomeArtistico;
        private List<Tag> listaTags;
        private Arte arte;
        private LocalDate dataNasc;

        // LOGRADOURO
        private String nomeLog;
        private Short numLog;
        private String cep;
        private String bairro;
        private String complemento;
        private String cidade;
        private String uf;

        private String textoBio;
        private Set<Seguida> seguidores;
        private Set<Seguida> seguido;
        private List<ContatoResponse> contatos;
        
        public builder setId(Long id) {
            this.id = id;
            return this;
        }
        public builder setNome(String nome) {
            this.nome = nome;
            return this;
        }
        public builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public builder setTipoConta(String tipoConta) {
            this.tipoConta = tipoConta;
            return this;
        }
        public builder setStatus(Status status) {
            this.status = status;
            return this;
        }
        public builder setDataCriacao(LocalDateTime dataCriacao) {
            this.dataCriacao = dataCriacao;
            return this;
        }
        public builder setNomeArtistico(String nomeArtistico) {
            this.nomeArtistico = nomeArtistico;
            return this;
        }
        public builder setListaTags(List<Tag> listaTags) {
            this.listaTags = listaTags;
            return this;
        }
        public builder setArte(Arte arte) {
            this.arte = arte;
            return this;
        }
        public builder setDataNasc(LocalDate dataNasc) {
            this.dataNasc = dataNasc;
            return this;
        }
        public builder setNomeLog(String nomeLog) {
            this.nomeLog = nomeLog;
            return this;
        }
        public builder setNumLog(Short numLog) {
            this.numLog = numLog;
            return this;
        }
        public builder setCep(String cep) {
            this.cep = cep;
            return this;
        }
        public builder setBairro(String bairro) {
            this.bairro = bairro;
            return this;
        }
        public builder setComplemento(String complemento) {
            this.complemento = complemento;
            return this;
        }
        public builder setCidade(String cidade) {
            this.cidade = cidade;
            return this;
        }
        public builder setUf(String uf) {
            this.uf = uf;
            return this;
        }
        public builder setTextoBio(String textoBio) {
            this.textoBio = textoBio;
            return this;
        }
        public builder setSeguidores(Set<Seguida> seguidores) {
            this.seguidores = seguidores;
            return this;
        }
        public builder setSeguido(Set<Seguida> seguido) {
            this.seguido = seguido;
            return this;
        }
        public builder setContatos(List<ContatoResponse> contatos) {
            this.contatos = contatos;
            return this;
        }

        public ArtistaResponse build() {
            return new ArtistaResponse(id, nome, email, tipoConta, status, dataCriacao, nomeArtistico, listaTags, arte, dataNasc, nomeLog, numLog, cep, bairro, complemento, cidade, uf, textoBio, seguidores, seguido, contatos);
        }

    }
    /* */
    public static ArtistaResponse toDTO(Artista m) {
        ArtistaResponse dto = new ArtistaResponse(m.getId(), m.getNome(), m.getEmail(),
        m.getTipoConta(), m.getStatus(), m.getDataCriacao(), m.getNomeArtistico(),
        m.getListaTags(),
        m.getArte(), m.getDataNasc(), m.getNomeLog(), m.getNumLog(), m.getCep(),
        m.getBairro(), m.getComplemento(), m.getCidade(), m.getUf(), m.getTextoBio(),
        m.getSeguidores(), m.getSeguido(),
        m.getContatos().stream().map(
                c->new ContatoResponse.builder()
                    .setIdContato(c.getId())
                    .setTipoContatoResponse(new TipoContatoResponse.builder()
                        .setIdTipoContato(c.getTipoContato().getId())
                        .setTipoContato(c.getTipoContato().getTipoContato())
                        .build()
                    )
                    .setValorContato(c.getValorContato())
                    .build())
                .collect(Collectors.toList())
        );
        return dto;
    }
}

