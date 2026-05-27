package corallus.artConnect.artConnect.dto.response.admin;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;

import java.util.List;

public record RelatorioResponse(
       Integer artistasCadastrados,
       Integer contratantesCadastrados,
       Integer contratantesPendentes,

       List<PublicacaoResponse> publicacoesSemanal,
       List<UsuarioResponse> usuarios
) {

    public static class builder {
        private Integer artistasCadastrados;
        private Integer contratantesCadastrados;
        private Integer contratantesPendentes;

        private List<PublicacaoResponse> publicacoesSemanal;
        private List<UsuarioResponse> usuarios;

        public builder setArtistasCadastrados(Integer artistasCadastrados) {
            this.artistasCadastrados = artistasCadastrados;
            return this;
        }

        public builder setContratantesCadastrados(Integer contratantesCadastrados) {
            this.contratantesCadastrados = contratantesCadastrados;
            return this;
        }

        public builder setContratantesPendentes(Integer contratantesPendentes) {
            this.contratantesPendentes = contratantesPendentes;
            return this;
        }

        public builder setPublicacoesSemanal(List<PublicacaoResponse> publicacoesSemanal) {
            this.publicacoesSemanal = publicacoesSemanal;
            return this;
        }

        public builder setUsuarios(List<UsuarioResponse> usuarios) {
            this.usuarios = usuarios;
            return this;
        }

        public RelatorioResponse build() {
            return new RelatorioResponse(artistasCadastrados, contratantesCadastrados, contratantesPendentes,
                    publicacoesSemanal, usuarios);
        }
    }


}
