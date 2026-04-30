package corallus.artConnect.artConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;

@Service
public class LoginService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario login(String email, String senha) {
		List<Usuario> usuarios = buscaUsuarios();
		Usuario alvo = null;
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail().equals(email)&&
			usuario.getSenha().equals(senha)) {
				if(usuario.getStatus().getTipoStatus().getId()==1L) {
					alvo = usuario;
				} else if(usuario.getStatus().getTipoStatus().getId()==2L) {
					throw new RuntimeException("Conta pendente");
				} else if(usuario.getStatus().getTipoStatus().getId()==3L) {
					throw new RuntimeException("Conta suspensa");
				}
			} else {
				throw new UserNotFoundException();
			}
		}
		return alvo;
	}
	
	private List<Usuario> buscaUsuarios() {
		List<Usuario> usuarios = new ArrayList<>(this.usuarioRepository.findAll());
		return usuarios;
	}
}
