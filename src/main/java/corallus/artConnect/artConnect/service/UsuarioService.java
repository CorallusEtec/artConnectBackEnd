package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.CommonEdit;
import corallus.artConnect.artConnect.enumeration.ETipoConta;
import corallus.artConnect.artConnect.mapper.usuario.UsuarioMapper;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    // INJEÇÃO DE DEPENDÊNCIA
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public Page<UsuarioResponse> findAll(UsuarioFindAllQF filter, Pageable pageable) {
        Page<Usuario> lista = this.usuarioRepository.findAll(filter.toSpecifications(), pageable);
        return lista.map(usuarioMapper::toDTO);
    }

    public UsuarioResponse findById(Long id) {
        Usuario entity = this.usuarioRepository.findById(id)
                .filter(u->u.getTipoConta() != ETipoConta.ADMIN)
            .orElseThrow(UserNotFoundException::new);
        return this.usuarioMapper.toDTO(entity);
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
