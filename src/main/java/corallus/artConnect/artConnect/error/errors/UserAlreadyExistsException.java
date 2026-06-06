package corallus.artConnect.artConnect.error.errors;

/**
 * Erro do tipo {@code 409}
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Não foi possível cadastrar: Email já existente.");
    }
    
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
