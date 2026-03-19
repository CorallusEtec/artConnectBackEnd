package corallus.artConnect.artConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Usuario;
import corallus.artConnect.artConnect.repository.AdminRepository;
import corallus.artConnect.artConnect.repository.ArtistaRepository;
import corallus.artConnect.artConnect.repository.EstabelecimentoRepository;

@Service
public class LoginService {
    
    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;
    @Autowired
    private AdminRepository adminRepository;
    
    
    
    public List<List<Usuario>> findAll() {
        List<List<Usuario>> usuarios = new ArrayList<>();
        usuarios.add(new ArrayList<>(this.artistaRepository.findAll()));
        usuarios.add(new ArrayList<>(this.estabelecimentoRepository.findAll())); 
        usuarios.add(new ArrayList<>(this.adminRepository.findAll()));
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

    public Usuario findByEmail(String email) {
    	List<List<Usuario>> lista = this.findAll();
    	for(int i=0; i<lista.size(); i++) {
    		for(int j=0; j<lista.get(i).size(); j++) {
    			if(lista.get(i).get(j).getEmail().equals(email)) {
        			return lista.get(i).get(j);
        		}
    		}
    	}
    	return null;
    }
    
    private List<Usuario> buscaUsuariosEmail() {
        List<Usuario> usuarios = new ArrayList<>(this.artistaRepository.findAll());
        usuarios.addAll(this.estabelecimentoRepository.findAll());
        usuarios.addAll(this.adminRepository.findAll());
        return usuarios;
    }
}
