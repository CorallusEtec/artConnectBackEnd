package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 409}
 */
public class GeneroArteAlreadyExistsException extends RuntimeException {
    public GeneroArteAlreadyExistsException() {
        super("Gênero de arte já existe.");
    }
}