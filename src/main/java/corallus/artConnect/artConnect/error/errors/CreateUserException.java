package corallus.artConnect.artConnect.error.errors;

public class CreateUserException extends RuntimeException {
    public CreateUserException(){
        super("Não foi possível cadastrar o usuário");
    }

    public CreateUserException(String message) {
        super(message);
    }
}
