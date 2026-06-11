package corallus.artConnect.artConnect.enumeration;

public enum ETipoReacao {
    LIKE,
    DISLIKE;


    /**
     * Atributo usado para validar requests que pedem tipo de reação.
     */
    public static final String tipoReacaoRegExp = "LIKE|DISLIKE";
}
