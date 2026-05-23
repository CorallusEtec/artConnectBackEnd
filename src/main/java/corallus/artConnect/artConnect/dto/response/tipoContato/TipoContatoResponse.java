package corallus.artConnect.artConnect.dto.response.tipoContato;

public record TipoContatoResponse(
    Long idTipoContato,
    String tipoContato
) {
    

    public static class builder {
        private Long idTipoContato;
        private String tipoContato;
        
        
        public builder setIdTipoContato(Long idTipoContato) {
            this.idTipoContato = idTipoContato;
            return this;
        }
        
        public builder setTipoContato(String tipoContato) {
            this.tipoContato = tipoContato;
            return this;
        }

        public TipoContatoResponse build() {
            return new TipoContatoResponse(idTipoContato, tipoContato);
        }
    }
}
