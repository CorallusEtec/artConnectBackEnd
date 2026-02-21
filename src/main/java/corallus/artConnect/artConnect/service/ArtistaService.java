package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.repository.ArtistasRepository;

@Service
public class ArtistaService {
	
	@Autowired
	private ArtistasRepository artistaRepository;
	
	
	public Artista find(String email, String senha) {
		List<Artista> lista = artistaRepository.findAll();
		Artista a = new Artista(email, senha);
		a = this.encontrarArtista(a, lista);
		return a;
	}
	
	private Artista encontrarArtista(Artista consulta, List<Artista> lista) {
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i).equals(consulta)) {
				return lista.get(i);
			}
		}
		return null;
	}
}
