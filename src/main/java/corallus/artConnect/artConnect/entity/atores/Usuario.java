package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDateTime;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import corallus.artConnect.artConnect.entity.*;
import corallus.artConnect.artConnect.entity.arte.Arte;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import corallus.artConnect.artConnect.entity.contato.Contato;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipoConta", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Dados Importantes
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private ETipoConta tipoConta;

    @OneToOne
    private Status status;
    private LocalDateTime dataCriacao;
    // LOGRADOURO / ENDEREÇO
    private String nomeLog;
    private Short numLog;
    private String cep;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
    // Outros atributos
    private String textoBio;
    private String fotoPerfilUrl;

    @OneToMany(mappedBy = "seguidor")
    private Set<Seguida> seguidores = new HashSet<>();

    @OneToMany(mappedBy = "seguido")
    private Set<Seguida> seguido = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private List<Contato> contatos = new ArrayList<>();

    @OneToMany(mappedBy = "autor")
    private List<Publicacao> publicacoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Reacao> reacoes = new HashSet<>();

    // Metodos UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.tipoConta.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
