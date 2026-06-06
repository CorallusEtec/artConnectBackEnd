package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.request.contato.ContatoEditRequest;
import corallus.artConnect.artConnect.dto.request.contato.ContatoSaveRequest;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.entity.contato.Contato;
import corallus.artConnect.artConnect.entity.contato.TipoContato;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.repository.contato.ContatoRepository;
import corallus.artConnect.artConnect.repository.contato.TipoContatoRepository;

import java.util.Objects;

@Service
public class ContatoService {

    private final TipoContatoRepository tipoContatoRepository;
    private final ContatoRepository contatoRepository;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContatoService(
            TipoContatoRepository tipoContatoRepository,
            ContatoRepository contatoRepository
    ){
        this.tipoContatoRepository = tipoContatoRepository;
        this.contatoRepository = contatoRepository;
    }

    public MessageResponse save(Usuario usuario, ContatoSaveRequest saveRequest) {
        // Buscando tipo de contato
        TipoContato tipoContato = this.tipoContatoRepository.findById(saveRequest.idTipoContato())
        .orElseThrow(()->new ResourceNotFoundException("Tipo de contato não encontrado ou inexistente"));

        // Criando contato
        Contato contato = new Contato();
        contato.setTipoContato(tipoContato);
        contato.setUsuario(usuario);
        contato.setValorContato(saveRequest.valorContato());

        this.contatoRepository.save(contato);
        return new MessageResponse("Contato adicionado");
    }

    public MessageResponse delete(Usuario usuario, Long idContato) {
        // USUARIO PRECISA ESTAR AUTENTICADO
        if(Objects.isNull(usuario)) {throw new NotAuthorizedException();}

        // VERIFICA SE O ID EXISTE [VALIDAÇÃO JÁ ACONTECE NO PRÓPRIO deleteById()]
        this.contatoRepository.deleteById(idContato);
        return new MessageResponse("Contato deletado com sucesso");
    }

    public MessageResponse edit(Usuario usuario, Long idContato, ContatoEditRequest editRequest) {
        // USUARIO PRECISA ESTAR AUTENTICADO
        if(Objects.isNull(usuario)) {throw new NotAuthorizedException();}

        // Buscando contato
        Contato contato = this.contatoRepository.findById(idContato)
        .orElseThrow(()->new ResourceNotFoundException("Contato não encontrado ou inexistente"));

       // Atribuindo novos valores
        contato.setValorContato(editRequest.valorContato());
        this.contatoRepository.save(contato);
        return new MessageResponse("Contato alterado com sucesso");
    }
}
