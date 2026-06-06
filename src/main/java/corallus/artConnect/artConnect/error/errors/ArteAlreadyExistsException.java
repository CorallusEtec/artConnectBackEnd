package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 409}
 */
public class ArteAlreadyExistsException extends RuntimeException {
    public ArteAlreadyExistsException(){
        super("Arte já cadastrada.");
    }

    public ArteAlreadyExistsException(String message) {
        super(message);
    }
}
