package corallus.artConnect.artConnect.service;

import java.util.List;
import java.util.stream.Collectors;

import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.dto.response.tipoContato.TipoContatoResponse;
import corallus.artConnect.artConnect.repository.contato.TipoContatoRepository;

@Service
public class TipoContatoService {
    @Autowired
    private TipoContatoRepository tipoContatoRepository;

    public List<TipoContatoResponse> findAll() {
        var lista =  this.tipoContatoRepository.findAll();

        return lista.stream()
        .map(c -> new TipoContatoResponse.builder()
                .setTipoContato(c.getTipoContato())
                .setIdTipoContato(c.getId())
                .build()
        )
                .collect(Collectors.toList());
    }

    public TipoContatoResponse find(Long id) {
        var tipoContato = this.tipoContatoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Tipo de contato não encontrado ou inexistente"));
        return new TipoContatoResponse.builder()
                .setIdTipoContato(tipoContato.getId())
                .setTipoContato(tipoContato.getTipoContato())
                .build();

    }
}
