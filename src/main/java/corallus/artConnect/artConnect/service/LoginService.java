package corallus.artConnect.artConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioLoginRequest;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;


@Service
public class LoginService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioLoginRequest login(String email, String senha) {
		List<Usuario> usuarios = buscaUsuarios();
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail() == null || usuario.getSenha() == null) {
				continue;
			}
			if(usuario.getEmail().equals(email)&&
			usuario.getSenha().equals(senha)) {
				return new UsuarioLoginRequest(
						usuario.getId(),
						usuario.getNome(),
						usuario.getStatus()
						);
			}
		}
		throw new RuntimeException("Email ou senha invalidos");
		
		
	}
	
	private List<Usuario> buscaUsuarios() {
		List<Usuario> usuarios = new ArrayList<>(this.usuarioRepository.findAll());
		return usuarios;
	}
}
