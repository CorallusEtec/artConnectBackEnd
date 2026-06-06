package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 404}
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário não encontrado");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
