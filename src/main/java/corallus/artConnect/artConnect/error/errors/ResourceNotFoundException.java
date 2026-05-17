package corallus.artConnect.artConnect.error.errors;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
