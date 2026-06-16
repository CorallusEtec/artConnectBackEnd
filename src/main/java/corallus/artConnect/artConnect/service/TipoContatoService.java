package corallus.artConnect.artConnect.service;

import java.util.List;

import corallus.artConnect.artConnect.dto.request.tipoContato.TipoContatoSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.contato.TipoContato;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.mapper.tipoContato.TipoContatoMapper;
import org.springframework.stereotype.Service;
import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.repository.contato.TipoContatoRepository;

@Service
public class TipoContatoService {

    private final TipoContatoRepository tipoContatoRepository;
    private final TipoContatoMapper tipoContatoMapper;

    // INJEÇÃO DE DEPENDÊNCIA
    public TipoContatoService(TipoContatoRepository tipoContatoRepository,
                              TipoContatoMapper tipoContatoMapper) {
        this.tipoContatoRepository = tipoContatoRepository;
        this.tipoContatoMapper = tipoContatoMapper;
    }

    public List<TipoContatoResponse> findAll() {
        var lista =  this.tipoContatoRepository.findAll();
        return this.tipoContatoMapper.toDTOList(lista);
    }

    public TipoContatoResponse findById(Long id) {
        var tipoContato = this.tipoContatoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Tipo de contato não encontrado ou inexistente"));
        return this.tipoContatoMapper.toDTO(tipoContato);
    }

    public MessageApiResponse save(TipoContatoSaveRequest saveRequest) {
        TipoContato tipo = new TipoContato();
        tipo.setTipoContato(saveRequest.tipoContato());

        this.tipoContatoRepository.save(tipo);
        return new MessageApiResponse("Tipo de Contato criado com sucesso");
    }
}
