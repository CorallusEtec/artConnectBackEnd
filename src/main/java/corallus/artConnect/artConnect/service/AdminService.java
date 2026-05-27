package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PublicacaoService publicacaoService;

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

        // Usuarios Cadastrados em 6 meses
        usuarioQf.setTipoConta(null);
        usuarioQf.setDataCriacaoStarts(LocalDateTime.now().minusMonths(6));
        List<UsuarioResponse> usuariosSemestre = this.usuarioService.supFindAll(usuarioQf);
        System.out.println("Fim");
        return new RelatorioResponse.builder()
                .setArtistasCadastrados(artistasCad)
                .setContratantesCadastrados(contratanteCad)
                .setContratantesPendentes(contratantesPend)
                .setUsuarios(usuariosSemestre)
                .setPublicacoesSemanal(publicacoesSem)
                .build();
    }



}
