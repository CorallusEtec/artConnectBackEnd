package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo 403
 */
public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException() {
        super("Você não tem permissão para realizar essa operação");
    }
    public NotAuthorizedException(String message) {
        super(message);
    }
}
