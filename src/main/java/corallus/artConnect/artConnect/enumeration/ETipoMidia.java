package corallus.artConnect.artConnect.enumeration;

public enum ETipoMidia {
    VIDEO,
    IMAGEM,
    AUDIO;

    /**
     * Atributo usado para validar requests que pedem tipo de mídia.
     */
    public static final String tipoMidiaRegExp = "VIDEO|IMAGEM|AUDIO";
}
