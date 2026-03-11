package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.tipoContato;
import corallus.artConnect.artConnect.repository.tipoContatoRepository;

@Service
public class tipoContatoService {
	@Autowired
    private tipoContatoRepository tipoContatoRepository;
    public List<tipoContato> findAll() {
        return tipoContatoRepository.findAll();
    }
}
