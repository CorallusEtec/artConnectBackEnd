package corallus.artConnect.artConnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Arte;


@Service
public class ArteService {
	private List<Arte> lista = new ArrayList<Arte>();
	
	public List<Arte> findAll() {
		lista.add(new Arte("Danca", "Descricao da danca"));
		lista.add(new Arte("ORquestra", "Descricao da orquestra"));
		return lista;
	}
	
	
}
