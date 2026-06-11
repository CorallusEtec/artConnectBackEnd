package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 424}
 */
public class CreateUserException extends RuntimeException {
    public CreateUserException(){
        super("Não foi possível cadastrar o usuário");
    }

    public CreateUserException(String message) {
        super(message);
    }
}
