package corallus.artConnect.artConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import corallus.artConnect.artConnect.dto.request.CommonEdit;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<UsuarioResponse> findAll(UsuarioFindAllQF filter) {
        return this.usuarioRepository.findAll(filter.toSpecifications())
                .stream()
                .filter(u->!(u.getTipoConta().equalsIgnoreCase("ADMIN")))
                .map(UsuarioResponse::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponse findById(Long id) {
        Usuario model = this.usuarioRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);
        if(model.getTipoConta().equalsIgnoreCase("ADMIN")) {
            throw new UserNotFoundException();
        }

        return UsuarioResponse.toDTO(model);
    }


    public static void fillCommonEdits(Usuario u, CommonEdit dto) {
        u.setNomeLog(dto.nomeLog());
        u.setNumLog(dto.numLog());
        u.setCep(dto.cep());
        u.setBairro(dto.bairro());
        u.setComplemento(dto.complemento());
        u.setCidade(dto.cidade());
        u.setUf(dto.uf());
    }
}
