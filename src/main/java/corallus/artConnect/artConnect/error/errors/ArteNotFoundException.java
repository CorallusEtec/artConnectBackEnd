package corallus.artConnect.artConnect.error.errors;

public class ArteNotFoundException extends RuntimeException{
    public ArteNotFoundException() {
        super("Arte não encontrado.");
    }

    public ArteNotFoundException(String msg) {
        super(msg);
    }
}
