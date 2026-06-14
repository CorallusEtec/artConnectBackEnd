package corallus.artConnect.artConnect.entity.atores;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {
    // CONSTRUTOR

    public Admin() {
    }
}
