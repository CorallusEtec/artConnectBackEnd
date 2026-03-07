package corallus.artConnect.artConnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Artista;
import corallus.artConnect.artConnect.repository.ArtistaRepository;

@Service
public class ArtistaService {
    
    @Autowired
    private ArtistaRepository artistaRepository;

    // Salva um novo artista no banco de dados
    public String cadastro(Artista artista) {
        try {
            artistaRepository.save(artista);
            return "Cadastro realizado com sucesso!";
        } catch (Exception e) {
            return "Erro ao cadastrar artista: " + e.getMessage();
        }
    }
    // Busca todos os artistas cadastrados no banco de dados
    public List<Artista> findAll() {
        try {
            List<Artista> artistas = artistaRepository.findAll();
            return artistas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar artistas: " + e.getMessage());
        }
    }

    //Metodo para troca de senha
    public String replacePass (Long idArtista, String novaSenha) {
        try{
        Optional<Artista> artistaOpt = artistaRepository.findById(idArtista);

        if(artistaOpt.isPresent()) {
            Artista artista = artistaOpt.get();

            artista.setSenha(novaSenha);
            artistaRepository.save(artista);

            return "Senha alterada";
        } else {
            return "id não encontrada: " + idArtista;
        }
        } catch(Exception e) {
            return "Erro ao alterar senha:" + e.getMessage();
        }
    } 

    public String alterarArtista(Artista artista) {
        try {
            if(artistaRepository.existsById(artista.getId())) {
                Artista artistaAlterado = modificarCamposArtista(artista);
                artistaRepository.save(artistaAlterado);
                return "Artista alterado com sucesso!";
            } else {
                throw new RuntimeException("Artista não encontrado: " + artista.getId());
            }
        } catch(Exception e) {
            return "Erro ao alterar artista: " + e.getMessage();
        }
    }

    private Artista modificarCamposArtista(Artista artistaMod) {
        Artista artistaExistente = artistaRepository.findById(artistaMod.getId()).get();
        Artista artistaAlterado = new Artista();
        artistaAlterado.setId(artistaExistente.getId());
        // Abaixo só altera os campos que foram modificados, os outros permanecem iguais
        artistaAlterado.setDataNasc(artistaMod.getDataNasc()!=null?artistaMod.getDataNasc():artistaExistente.getDataNasc());
        artistaAlterado.setCpf(artistaMod.getCpf()!=null?artistaMod.getCpf():artistaExistente.getCpf());
        artistaAlterado.setSexo(artistaMod.getSexo()!=null?artistaMod.getSexo():artistaExistente.getSexo());
        artistaAlterado.setTelefone(artistaMod.getTelefone()!=null?artistaMod.getTelefone():artistaExistente.getTelefone());
        artistaAlterado.setBairro(artistaMod.getBairro()!=null?artistaMod.getBairro():artistaExistente.getBairro());
        artistaAlterado.setCep(artistaMod.getCep()!=null?artistaMod.getCep():artistaExistente.getCep());
        artistaAlterado.setCidade(artistaMod.getCidade()!=null?artistaMod.getCidade():artistaExistente.getCidade());
        artistaAlterado.setComplemento(artistaMod.getComplemento()!=null?artistaMod.getComplemento():artistaExistente.getComplemento());
        artistaAlterado.setEmail(artistaMod.getEmail()!=null?artistaMod.getEmail():artistaExistente.getEmail());
        artistaAlterado.setEstado(artistaMod.getEstado()!=null?artistaMod.getEstado():artistaExistente.getEstado());
        artistaAlterado.setNome(artistaMod.getNome()!=null?artistaMod.getNome():artistaExistente.getNome());
        artistaAlterado.setNomeLog(artistaMod.getNomeLog()!=null?artistaMod.getNomeLog():artistaExistente.getNomeLog());
        artistaAlterado.setNumLog(artistaMod.getNumLog()!=null?artistaMod.getNumLog():artistaExistente.getNumLog());
        artistaAlterado.setSenha(artistaMod.getSenha()!=null?artistaMod.getSenha():artistaExistente.getSenha());
        artistaAlterado.setTipoLog(artistaMod.getTipoLog()!=null?artistaMod.getTipoLog():artistaExistente.getTipoLog());
        return artistaAlterado;
    }
}
