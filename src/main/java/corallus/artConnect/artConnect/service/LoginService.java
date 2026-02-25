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


    public Usuario login(String email, String senha) {
        List<Usuario> usuarios = buscaUsuariosEmail();
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }

    private List<Usuario> buscaUsuariosEmail() {
        List<Usuario> usuarios = new ArrayList<>(artistaRepository.findAll());
        usuarios.addAll(estabelecimentoRepository.findAll());
        return usuarios;
    }
}
