package corallus.artConnect.artConnect.error.errors;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Usuário não encontrado");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
