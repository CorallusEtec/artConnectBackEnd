package corallus.artConnect.artConnect.dto.response.contato;

import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;

public record ContatoResponse(
    Long idContato,
    TipoContatoResponse tipoContato,
    String valorContato
) {
    public static class builder {
        private Long idContato;
        private TipoContatoResponse tipoContatoResponse;
        private String valorContato;

        public builder setIdContato(Long idContato) {
            this.idContato = idContato;
            return this;
        }
        public builder setTipoContatoResponse(TipoContatoResponse tipoContatoResponse) {
            this.tipoContatoResponse = tipoContatoResponse;
            return this;
        }
        public builder setValorContato(String valorContato) {
            this.valorContato = valorContato;
            return this;
        }

        public ContatoResponse build() {
            return new ContatoResponse(idContato, tipoContatoResponse, valorContato);
        }


    }
}
