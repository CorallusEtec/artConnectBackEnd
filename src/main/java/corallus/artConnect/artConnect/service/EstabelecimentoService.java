package corallus.artConnect.artConnect.service;

import java.util.List;
import java.util.Optional;

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

	public Estabelecimento findById(Long id) {
		try {
			Estabelecimento es = this.estabelecimentoRepository.findById(id).get();
			return es;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//Metodo para troca de senha
    public String replacePass (Long idEstabelecimento, String novaSenha) {
        try{
        Optional<Estabelecimento> estabelecimentoOpt = estabelecimentoRepository.findById(idEstabelecimento);

        if(estabelecimentoOpt.isPresent()) {
            Estabelecimento estabelecimento = estabelecimentoOpt.get();

            estabelecimento.setSenha(novaSenha);
            estabelecimentoRepository.save(estabelecimento);

            return "Senha alterada";
        } else {
            return "id não encontrada: " + idEstabelecimento;
        }
        } catch(Exception e) {
            return "Erro ao alterar senha:" + e.getMessage();
        }
    } 

	public String alterarEstabelecimento(Long id, Estabelecimento estabelecimento) {
		try {
			if(estabelecimentoRepository.existsById(id)) {
				estabelecimento.setId(id);
				estabelecimentoRepository.save(estabelecimento);
				return "Estabelecimento alterado!";
			} else {
				throw new RuntimeException("Estabelecimento não encontrado");
			}
		} catch (Exception e) {
			return "Não foi possivel alterar estabelecimento " + e.getMessage();
		}
	}
}
