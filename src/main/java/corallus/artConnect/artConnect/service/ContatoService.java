package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContatoService implements IValidacoes{

    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private ContatoRepository contatoRepository;

    public MessageResponse save(ContatoSaveRequest contatoDto) {
        // Validando conteúdo do contato
        this.validarString("O valor do contato não pode ser vazio", new String[] {contatoDto.valorContato()});

        // Buscando usuario
        Usuario u = this.usuarioRepository.findById(contatoDto.idUsuario())
        .orElseThrow(()->new UserNotFoundException());

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
        // Validando Request
        this.validarString(null, new String[] {editRequest.valorContato()});

        // Buscando contato
        Contato contato = this.contatoRepository.findById(idContato)
        .orElseThrow(()->new ResourceNotFoundException("Contato não encontrado ou inexistente"));
        
        contato.setValorContato(editRequest.valorContato());

        this.contatoRepository.save(contato);

        return new MessageResponse("Contato alterado com sucesso");
    }
    
    @Override
    public void validarString(String msgErro, String[] campos) {
        for(String c : campos) {
            if(c == (null) || c.trim().isEmpty()) {
                if(msgErro == null) {
                    throw new IllegalArgumentException("Há campos vazios na requisição.");
                } else {
                    throw new IllegalArgumentException(msgErro);
                }
            }
        }
        
    }

    
}
