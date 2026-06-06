package corallus.artConnect.artConnect.service;

import java.io.IOException;
import java.util.*;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoPublicacaoResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.reacao.Reacao;
import corallus.artConnect.artConnect.enumeration.ETipoReacao;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.mapper.publicacao.PublicacaoDetailsMapper;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.entity.Publicacao;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.repository.PublicacaoRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class PublicacaoService {

    private final PublicacaoRepository publicacaoRepository;
    private final StatusService statusService;
    private final S3Client s3Client;
    private final PublicacaoDetailsMapper publicacaoDetailsMapper;

    // INJEÇÃO DE DEPENDÊNCIA
    public PublicacaoService(
            PublicacaoRepository publicacaoRepository,
            StatusService statusService,
            S3Client s3Client,
            PublicacaoDetailsMapper publicacaoDetailsMapper
    ){
        this.publicacaoRepository = publicacaoRepository;
        this.statusService = statusService;
        this.s3Client = s3Client;
        this.publicacaoDetailsMapper = publicacaoDetailsMapper;
    }

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public MessageResponse save(String legenda, MultipartFile file, Usuario autor) throws IOException {
            boolean temLegenda = legenda != null && !legenda.isBlank();
            boolean temImagem = file != null && !file.isEmpty();

            if (!temLegenda && !temImagem) {
                throw new IllegalArgumentException("O Post deve ter ao menos imagem ou legenda");
            }

            String url = null;
            if (temImagem) {

                String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(fileName)
                                .contentType(file.getContentType())
                                .build(),
                        RequestBody.fromBytes(file.getBytes())
                );
                url = "https://" + bucketName + ".s3.sa-east-1.amazonaws.com/" + fileName;
            }

            Publicacao pub = new Publicacao();
            pub.setLegenda(temLegenda ? legenda : null);
            pub.setUrlMidia(url);
            pub.setAutor(autor);

            // STATUS PADRÃO DE CRIAÇÂO: ATIVO
            pub.setStatusPublicacao(this.statusService.generateStatus());

            publicacaoRepository.save(pub);
            return new MessageResponse("Postagem criada com sucesso!");
    }

    public List<PublicacaoResponse> findAll(PublicacaoFindAllQF find, Usuario usuario) {
        List<Publicacao> publicacaoList = this.publicacaoRepository.findAll(find.toSpecifications());

        // CONVERTE OS DADOS DA PUBLICAÇÃO
        return publicacaoList.stream()
                .map(p->this.getPublicacaoResponse(p, usuario))
                .toList();
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
                .totalComentarios(Math.toIntExact(publicacao.getComentarios().stream().filter(c->c.getStatusComentario()
                        .getTipoStatus().getNomeTipoStatus() == ETipoStatus.ATIVO).count()))
                .publicacao(this.publicacaoDetailsMapper.toDTO(publicacao))
                .reacoes(this.getReacaoPublicacao(publicacao.getReacoes()))
                .reacaoUsuario(getReacaoUsuario(publicacao, usuario))
                .build();
    }

    /**
     * Metodo que desacopla a busca pela reação do usuario autenticado na publicação
     *
     * @param publicacao Entidade de publicação que o usuário reagiu
     * @param usuario Referência do usuário auntenticado para busca de sua reação.
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
        return reacao.map(r -> r.getTipoReacao().getNomeTipo()).orElse(null);
    }


    /**
     * Metodo que retorna uma lista com as totalidades de cada reação de uma publicação.
     * @param reacoes Set de Reações, oriundas da publicação.
     * @return Retorna uma lista com o total de cada reação.
     */
    private List<ReacaoPublicacaoResponse> getReacaoPublicacao(Set<Reacao> reacoes) {
        List<ReacaoPublicacaoResponse> lista = new ArrayList<>();
        for (ETipoReacao t : ETipoReacao.values()) {
            var reacaoPublicacao = ReacaoPublicacaoResponse.builder()
                    .tipoReacao(t)
                    .total(Math.toIntExact(reacoes.stream()
                            .filter(r->r.getTipoReacao().getNomeTipo()==t).count()))
                    .build();
            lista.add(reacaoPublicacao);
        }
        return lista;
    }
}