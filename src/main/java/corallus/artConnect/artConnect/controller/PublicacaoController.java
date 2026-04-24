package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.dto.publicacao.PublicacaoDTO;
import corallus.artConnect.artConnect.service.PublicacaoService;
import corallus.artConnect.artConnect.service.S3Service;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    private final PublicacaoService publicacaoService;

    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    @PostMapping
    public PublicacaoDTO criarPublicacao(
    		@RequestPart(value = "legenda", required = false) String legenda,
            @RequestPart("file") MultipartFile image,
            @RequestParam Long autorId
    ) {
        return publicacaoService.criarPublicacao(legenda, image, autorId);
    }

    @GetMapping
    public List<PublicacaoDTO> listarPublicacoes() {
        return publicacaoService.listarPublicacoes();
    }
}

