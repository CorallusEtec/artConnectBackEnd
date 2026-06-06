package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 404}
 *
 */
public class ArteNotFoundException extends RuntimeException{
    public ArteNotFoundException() {
        super("Arte não encontrada.");
    }

    public ArteNotFoundException(String msg) {
        super(msg);
    }
}
