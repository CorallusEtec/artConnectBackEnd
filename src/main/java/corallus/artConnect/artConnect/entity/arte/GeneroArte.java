package corallus.artConnect.artConnect.entity.arte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

/**
 *
 * SERÁ IMPLEMENTADO ATÉ DIA 18/06/2026
 */

public class GeneroArte {

    private Long id;
    private String nomeGeneroArte;

    private Arte arte;

    // Construtor

    public GeneroArte() {

    }

    public GeneroArte(Long id, String nomeGeneroArte, Arte arte) {
        this.id = id;
        this.nomeGeneroArte = nomeGeneroArte;
        this.arte = arte;
    }

    // GET E SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeGeneroArte() {
        return nomeGeneroArte;
    }

    public void setNomeGeneroArte(String nomeGeneroArte) {
        this.nomeGeneroArte = nomeGeneroArte;
    }

    public Arte getArte() {
        return arte;
    }

    public void setArte(Arte arte) {
        this.arte = arte;
    }
}
