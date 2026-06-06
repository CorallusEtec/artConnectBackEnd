package corallus.artConnect.artConnect.enumeration;
/**
 * Enumeração com os tipos de usuário (conta) que usam a aplicação
 * @author SamuMeneDev
 */
public enum ETipoConta {
    ARTISTA,
    CONTRATANTE,
    ADMIN;

    /**
     * Atributo usado para validar requests que pedem tipo de conta.
     */
    public static final String tipoContaRegExp = "ARTISTA|CONTRATANTE|ADMIN";
}
