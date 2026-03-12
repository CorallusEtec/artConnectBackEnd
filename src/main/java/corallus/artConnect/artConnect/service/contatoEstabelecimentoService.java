package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.ContatoEstabelecimento;
import corallus.artConnect.artConnect.repository.ContatoEstabelecimentoRepository;

@Service
public class ContatoEstabelecimentoService {
    @Autowired
    private ContatoEstabelecimentoRepository contatoEstabelecimentoRepository;
    public List<ContatoEstabelecimento> findAll() {
        return contatoEstabelecimentoRepository.findAll();
    }
}
