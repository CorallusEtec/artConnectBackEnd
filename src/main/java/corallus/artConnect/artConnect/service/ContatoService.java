package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.contato.ContatoEditRequest;
import corallus.artConnect.artConnect.dto.request.contato.ContatoSaveRequest;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.contato.TipoContato;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.repository.contato.ContatoRepository;
import corallus.artConnect.artConnect.repository.contato.TipoContatoRepository;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;

@Service
public class ContatoService {

    private final TipoContatoRepository tipoContatoRepository;

    private final UsuarioRepository usuarioRepository;

    private final ContatoRepository contatoRepository;

    public ContatoService(TipoContatoRepository tipoContatoRepository,
                          UsuarioRepository usuarioRepository,
                          ContatoRepository contatoRepository) {
        this.tipoContatoRepository = tipoContatoRepository;
        this.usuarioRepository = usuarioRepository;
        this.contatoRepository = contatoRepository;
    }

    public MessageResponse save(ContatoSaveRequest contatoDto) {
        // Buscando usuario
        Usuario u = this.usuarioRepository.findById(contatoDto.idUsuario())
        .orElseThrow(UserNotFoundException::new);

        // Buscando tipo de contato
        TipoContato t = this.tipoContatoRepository.findById(contatoDto.idTipoContato())
        .orElseThrow(()->new ResourceNotFoundException("Tipo de contato não encontrado ou inexistente"));

        // Criando contato
        Contato contato = new Contato();
        contato.setTipoContato(t);
        contato.setUsuario(u);
        contato.setValorContato(contatoDto.valorContato());

        this.contatoRepository.save(contato);
        return new MessageResponse("Contato adicionado");
    }

    public MessageResponse delete(Long idContato) {
        if(!this.contatoRepository.existsById(idContato)) {
            throw new IllegalArgumentException("Contato não encontrado ou inexistente");
        }
        this.contatoRepository.deleteById(idContato);
        return new MessageResponse("Contato deletado com sucesso");
    }

    public MessageResponse edit(Long idContato, ContatoEditRequest editRequest) {
        // Buscando contato
        Contato contato = this.contatoRepository.findById(idContato)
        .orElseThrow(()->new ResourceNotFoundException("Contato não encontrado ou inexistente"));
        contato.setValorContato(editRequest.valorContato());

        this.contatoRepository.save(contato);
        return new MessageResponse("Contato alterado com sucesso");
    }
}
