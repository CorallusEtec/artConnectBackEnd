package corallus.artConnect.artConnect.dto.response.reacao;

import corallus.artConnect.artConnect.entity.reacao.TipoReacao;

public record ReacaoDetailsResponse(
        boolean ativo, // Se o usuario reagiu
        Integer totalReacoes,
    TipoReacao tipoReacao
) {}
