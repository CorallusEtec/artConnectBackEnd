package corallus.artConnect.artConnect.entity.atores;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import corallus.artConnect.artConnect.entity.Seguida;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.publicacao.Publicacao;
import corallus.artConnect.artConnect.entity.publicacao.Reacao;
import jakarta.persistence.Entity;

@Entity
public class Admin extends Usuario {

  

    // CONSTRUTOR

    public Admin() {
    }

    public Admin(Long id, String nome, String email, String senha, String tipoConta, Status status,
            LocalDateTime dataCriacao, String nomeLog, Short numLog, String cep, String bairro, String complemento,
            String cidade, String uf, String textoBio, Set<Seguida> seguidores, Set<Seguida> seguido,
            List<Contato> contatos, List<Publicacao> publicacoes, Set<Reacao> reacoes) {
        super(id, nome, email, senha, tipoConta, status, dataCriacao, nomeLog, numLog, cep, bairro, complemento, cidade,
                uf, textoBio, seguidores, seguido, contatos, publicacoes, reacoes);
    }

   
    

}
