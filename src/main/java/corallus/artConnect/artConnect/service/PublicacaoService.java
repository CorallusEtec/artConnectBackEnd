package corallus.artConnect.artConnect.service;

import java.util.*;
import corallus.artConnect.artConnect.dto.request.publicacao.PublicacaoSaveRequest;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoPublicacaoResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.Reacao;
import corallus.artConnect.artConnect.enumeration.ETipoMidia;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.mapper.publicacao.PublicacaoDetailsMapper;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final StatusService statusService;
    private final S3Service s3Service;
    private final PublicacaoDetailsMapper publicacaoDetailsMapper;

    // INJEÇÃO DE DEPENDÊNCIA
    public PublicacaoService(
            PublicacaoRepository publicacaoRepository,
            StatusService statusService,
            S3Service s3Service,
            PublicacaoDetailsMapper publicacaoDetailsMapper
    ){
        this.publicacaoRepository = publicacaoRepository;
        this.statusService = statusService;
        this.s3Service = s3Service;
        this.publicacaoDetailsMapper = publicacaoDetailsMapper;
    }

    public MessageResponse save(PublicacaoSaveRequest saveRequest, Usuario autor) throws Exception {
        boolean temLegenda = saveRequest.legenda() != null && !saveRequest.legenda().isBlank();
        boolean temArquivo = saveRequest.arquivo() != null && !saveRequest.arquivo().isEmpty();

        // SE NÃO TIVER NEM ARQUIVO, NEM LEGENDA
        if (!temLegenda && !temArquivo) {
            throw new IllegalArgumentException("O Post deve ter ao menos imagem ou legenda");
        }

        // VALIDA SE O TIPO DE MIDIA É VALIDO
        ETipoMidia tipoMidia = this.parseTipoMidia(saveRequest.tipoMidia());
        String url = null;
        // SE O TIPO DE MIDIA NÃO FOR NULL
        if(Objects.nonNull(saveRequest.tipoMidia())) {
            url = s3Service.uploadArquivoPublicacao(saveRequest.arquivo(), tipoMidia);
        }
            Publicacao pub = new Publicacao();
            pub.setLegenda(saveRequest.legenda());
            pub.setUrlMidia(url);
            pub.setAutor(autor);
            pub.setTipoMidia(tipoMidia);

            // STATUS PADRÃO DE CRIAÇÂO: ATIVO
            pub.setStatusPublicacao(this.statusService.generateStatus());
            publicacaoRepository.save(pub);
            return new MessageResponse("Postagem criada com sucesso!");
    }

    public Page<PublicacaoResponse> findAll(PublicacaoFindAllQF find, Usuario usuario, Pageable pageable) {
        Page<Publicacao> publicacaoList = this.publicacaoRepository.findAll(find.toSpecifications(), pageable);
        // CONVERTE OS DADOS DA PUBLICAÇÃO
        return publicacaoList.map(p->this.getPublicacaoResponse(p, usuario));
    }

    /**
     * Metodo que desacopla a instância da DTO, e busca os outros dados para response completa.
     *
     * @param publicacao Entidade de Publicação que serve de base para a response.
     * @param usuario Entidade do usuário autenticado que fez a requisição.
     * Caso o usuário faça a requisição sem se autenticar (referência nula),
     * a reação do usuario sobre a publicação será null.
     * @return Retorna a DTO criada com dados da publicação,
     * total de comentários, reação do usuário (se estiver autenticado),
     * e dados das reações em geral.
     */
    private PublicacaoResponse getPublicacaoResponse(Publicacao publicacao, Usuario usuario) {
        return PublicacaoResponse.builder()
                .totalComentarios(Math.toIntExact(publicacao.getComentarios().stream().filter(c->c.getStatus()
                        .getTipoStatus() == ETipoStatus.ATIVO).count()))
                .publicacao(this.publicacaoDetailsMapper.toDTO(publicacao))
                .likes(publicacao.getReacoes().stream().filter(r->r.getTipoReacao() == ETipoReacao.LIKE).count())
                .dislikes(publicacao.getReacoes().stream().filter(r->r.getTipoReacao()==ETipoReacao.DISLIKE).count())
                .reacaoUsuario(getReacaoUsuario(publicacao, usuario))
                .build();
    }

    /**
     * Metodo que desacopla a busca pela reação do usuario autenticado na publicação
     *
     * @param publicacao Entidade de publicação que o usuário reagiu
     * @param usuario Referência do usuário auntenticado para busca da sua reação.
     * @return Retorna a reação que o usuário fez nessa publicação.
     * Caso a referência do objeto usuario seja nula (não autênticado) ou não tem reação nessa
     * publicação, o retorno será null.
     */
    private ETipoReacao getReacaoUsuario(Publicacao publicacao, Usuario usuario) {
        if(Objects.isNull(usuario)) return null;
        Optional<Reacao> reacao =  publicacao.getReacoes()
                .stream()
                .filter(r->r.getUsuario()
                        .getId()
                        .equals(usuario.getId()))
                .findFirst();
        return reacao.map(Reacao::getTipoReacao).orElse(null);
    }

    private ETipoMidia parseTipoMidia(String tipoMidiaStr) {
        try {
            if(Objects.isNull(tipoMidiaStr)) {
                return null;
            } else {
                return ETipoMidia.valueOf(tipoMidiaStr.toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Tipo inválido: '" + tipoMidiaStr + "'. Aceitos: " + Arrays.toString(ETipoMidia.values())
            );
        }
    }
}