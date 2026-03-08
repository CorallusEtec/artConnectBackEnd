package corallus.artConnect.artConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corallus.artConnect.artConnect.entity.Arte;
import corallus.artConnect.artConnect.repository.ArteRepository;

@Service
public class ArteService {
    @Autowired
    private ArteRepository arteRepository;
    public List<Arte> findAll() {
        return arteRepository.findAll();
    }
}
