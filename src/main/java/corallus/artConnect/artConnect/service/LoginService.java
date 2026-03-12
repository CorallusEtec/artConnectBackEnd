package corallus.artConnect.artConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Usuario;
import corallus.artConnect.artConnect.repository.ArtistaRepository;
import corallus.artConnect.artConnect.repository.EstabelecimentoRepository;

@Service
public class LoginService {
    
    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public List<List<Usuario>> findAll() {
        List<List<Usuario>> usuarios = new ArrayList<>();
        usuarios.add(new ArrayList<>(artistaRepository.findAll()));
        usuarios.add(new ArrayList<>(estabelecimentoRepository.findAll())); 
        return usuarios;
    }

    public Usuario login(String email, String senha) {
        List<Usuario> usuarios = buscaUsuariosEmail();
        Usuario alvo = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                alvo = usuario;
            }
        }
        if(alvo == null) {
            throw new RuntimeException("Usuário não encontrado");
        }
        return alvo;
    }

    private List<Usuario> buscaUsuariosEmail() {
        List<Usuario> usuarios = new ArrayList<>(artistaRepository.findAll());
        usuarios.addAll(estabelecimentoRepository.findAll());
        return usuarios;
    }
}
