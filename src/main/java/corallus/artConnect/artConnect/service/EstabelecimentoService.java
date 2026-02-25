package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Estabelecimento;
import corallus.artConnect.artConnect.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoService {

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	public String cadastro(Estabelecimento estabelecimento) {
		try {
			estabelecimentoRepository.save(estabelecimento);
			return "Estabelecimento cadastrado!";
		} catch (Exception e) {
			return "Não foi possivel cadastrar estabelecimento " + e.getMessage();
		}
	}

	public String teste() {
		return "Olá Mundo";
	}

	public List<Estabelecimento> findAll() {
		List<Estabelecimento> lista = estabelecimentoRepository.findAll();
		return lista;
	}
}
