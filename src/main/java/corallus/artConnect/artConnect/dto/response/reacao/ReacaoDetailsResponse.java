package corallus.artConnect.artConnect.dto.response.reacao;

import corallus.artConnect.artConnect.entity.reacao.TipoReacao;

public record ReacaoDetailsResponse(
    Integer totalReacoes,
    TipoReacao tipoReacao
) {

    public static class builder {
        private Integer totalReacoes;
        private TipoReacao tipoReacao;

        public builder totalReacoes(Integer totalReacoes) {
            this.totalReacoes = totalReacoes;
            return this;
        }
        
        public builder tipoReacao(TipoReacao tipoReacao) {
            this.tipoReacao = tipoReacao;
            return this;
        }

        public ReacaoDetailsResponse build() {
            return new ReacaoDetailsResponse(totalReacoes, tipoReacao);
        }
    }

    public static ReacaoDetailsResponse toDTO(Integer totalReacoes, TipoReacao tipoReacao) {
            ReacaoDetailsResponse dto = new ReacaoDetailsResponse.builder()
            .totalReacoes(totalReacoes)
            .tipoReacao(tipoReacao)
            .build();
          return dto;
        }

}
