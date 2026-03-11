package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.contatoEstabelecimento;
import corallus.artConnect.artConnect.repository.contatoEstabelecimentoRepository;

@Service
public class contatoEstabelecimentoService {
    @Autowired
    private contatoEstabelecimentoRepository contatoEstabelecimentoRepository;
    public List<contatoEstabelecimento> findAll() {
        return contatoEstabelecimentoRepository.findAll();
    }
}
