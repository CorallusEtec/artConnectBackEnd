package corallus.artConnect.artConnect.enumeration;

public enum ETipoStatus {
    ATIVO,
    PENDENTE,
    SUSPENSO,
    EXCLUIDO,
    BANIDO,
    ARQUIVADO,
    CONCLUIDO;

    public static final String tipoStatusDenunciaRegEx = "ARQUIVADO|CONCLUIDO|ATIVO";
    public static final String tipoStatusUsuarioRegEx = "SUSPENSO|BANIDO|ATIVO";
    public static final String tipoStatusComentarioRegEx = "EXCLUIDO|ATIVO";
    public static final String tipoStatusPublicacaoRegEx = "EXCLUIDO|ATIVO";
}
