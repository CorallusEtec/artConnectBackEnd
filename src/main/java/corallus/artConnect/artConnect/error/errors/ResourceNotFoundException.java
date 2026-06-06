package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 404}
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
