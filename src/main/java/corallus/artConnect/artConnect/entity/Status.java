package corallus.artConnect.artConnect.entity;

public enum Status {
    ATIVO("ATIVO"),
    INATIVO("INATIVO"),
    SUSPENSA("SUSPENSA");

    private String nome;

    private Status(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }
}
