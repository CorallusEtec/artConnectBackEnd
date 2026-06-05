package corallus.artConnect.artConnect.error.errors;

public class GeneroArteAlreadyExistsException extends RuntimeException {
    public GeneroArteAlreadyExistsException() {
        super("Gênero de arte já existe.");
    }
}