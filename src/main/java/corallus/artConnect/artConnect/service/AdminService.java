package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PublicacaoService publicacaoService;

    @Autowired
    private ArteService arteService;

    public RelatorioResponse getRelatorio() {

        // QUERY FILTER DE USUARIO
        var usuarioQf = new UsuarioFindAllQF();
        System.out.println("Teste");
        // BUSCANDO ARTISTAS
        usuarioQf.setTipoConta("ARTISTA");
        Integer artistasCad = this.usuarioService.findAll(usuarioQf).size();

        // BUSCANDO CONTRATANTES
        usuarioQf.setTipoConta("CONTRATANTE");
        Integer contratanteCad = this.usuarioService.findAll(usuarioQf).size();

        // BUSCANDO PENDENTES
        Integer contratantesPend = this.usuarioService.findAll(usuarioQf, "PENDENTE").size();

        // PUBLICAÇÕES NOS ULTIMOS 7 DIAS
        var publiQf = new PublicacaoFindAllQF();
        publiQf.setDataInicio(LocalDateTime.now().minusDays(7));

        List<PublicacaoResponse> publicacoesSem = this.publicacaoService.findAll(publiQf);

        // ARTES MAIS POPULARES NO SISTEMA

        var artes =  this.arteService.findAll();

        var artesPopulares = artes.stream()
                .sorted(Comparator.comparingInt((Arte a) -> a.getArtistas().size()).reversed())
                .map(arte ->{
                    Map<String, Integer> mapaArte = new HashMap<>();
                    mapaArte.put("id", arte.getId().intValue());
                    mapaArte.put(arte.getNomeArte(), arte.getArtistas().size());

                    return mapaArte;
                })
                .toList();

        usuarioQf.setTipoConta(null);
        return new RelatorioResponse.builder()
                .setArtistasCadastrados(artistasCad)
                .setContratantesCadastrados(contratanteCad)
                .setContratantesPendentes(contratantesPend)
                .setPublicacoesSemanal(publicacoesSem)
                .setListaArtes(artesPopulares)
                .setUsuarios(this.usuarioService.findAll(usuarioQf))
                .build();
    }



}
