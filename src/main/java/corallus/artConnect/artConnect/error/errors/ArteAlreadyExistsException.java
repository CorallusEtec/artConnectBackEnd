package corallus.artConnect.artConnect.error.errors;

public class ArteAlreadyExistsException extends RuntimeException {
    public ArteAlreadyExistsException(){
        super("Arte já cadastrada");
    }

    public ArteAlreadyExistsException(String message) {
        super(message);
    }
}
