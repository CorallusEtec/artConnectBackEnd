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

	public String alterarEstabelecimento(Estabelecimento estabelecimento) {
		try {
			if(estabelecimentoRepository.existsById(estabelecimento.getId())) {
				Estabelecimento estabelecimentoAlterado = modificarCamposEstabelecimento(estabelecimento);
				estabelecimentoRepository.save(estabelecimentoAlterado);
				return "Estabelecimento alterado!";
			} else {
				throw new RuntimeException("Estabelecimento não encontrado");
			}
		} catch (Exception e) {
			return "Não foi possivel alterar estabelecimento " + e.getMessage();
		}
	}
	private Estabelecimento modificarCamposEstabelecimento(Estabelecimento estabelecimentoMod) {
        Estabelecimento estabelecimentoExistente = estabelecimentoRepository.findById(estabelecimentoMod.getId()).get();
        Estabelecimento estabelecimentoAlterado = new Estabelecimento();
        estabelecimentoAlterado.setId(estabelecimentoExistente.getId());
        // Abaixo só altera os campos que foram modificados, os outros permanecem iguais
        estabelecimentoAlterado.setCnpj(estabelecimentoMod.getCnpj()!=null?estabelecimentoMod.getCnpj():estabelecimentoExistente.getCnpj());
        estabelecimentoAlterado.setRazaoSocial(estabelecimentoMod.getRazaoSocial()!=null?estabelecimentoMod.getRazaoSocial():estabelecimentoExistente.getRazaoSocial());
        estabelecimentoAlterado.setBairro(estabelecimentoMod.getBairro()!=null?estabelecimentoMod.getBairro():estabelecimentoExistente.getBairro());
        estabelecimentoAlterado.setCep(estabelecimentoMod.getCep()!=null?estabelecimentoMod.getCep():estabelecimentoExistente.getCep());
        estabelecimentoAlterado.setCidade(estabelecimentoMod.getCidade()!=null?estabelecimentoMod.getCidade():estabelecimentoExistente.getCidade());
        estabelecimentoAlterado.setComplemento(estabelecimentoMod.getComplemento()!=null?estabelecimentoMod.getComplemento():estabelecimentoExistente.getComplemento());
        estabelecimentoAlterado.setEmail(estabelecimentoMod.getEmail()!=null?estabelecimentoMod.getEmail():estabelecimentoExistente.getEmail());
        estabelecimentoAlterado.setEstado(estabelecimentoMod.getEstado()!=null?estabelecimentoMod.getEstado():estabelecimentoExistente.getEstado());
        estabelecimentoAlterado.setNome(estabelecimentoMod.getNome()!=null?estabelecimentoMod.getNome():estabelecimentoExistente.getNome());
        estabelecimentoAlterado.setNomeLog(estabelecimentoMod.getNomeLog()!=null?estabelecimentoMod.getNomeLog():estabelecimentoExistente.getNomeLog());
        estabelecimentoAlterado.setNumLog(estabelecimentoMod.getNumLog()!=null?estabelecimentoMod.getNumLog():estabelecimentoExistente.getNumLog());
        estabelecimentoAlterado.setSenha(estabelecimentoMod.getSenha()!=null?estabelecimentoMod.getSenha():estabelecimentoExistente.getSenha());
        estabelecimentoAlterado.setTipoLog(estabelecimentoMod.getTipoLog()!=null?estabelecimentoMod.getTipoLog():estabelecimentoExistente.getTipoLog());
        return estabelecimentoAlterado;
    }
}
