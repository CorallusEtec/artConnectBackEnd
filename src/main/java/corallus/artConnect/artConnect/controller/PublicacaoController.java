package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.dto.publicacao.PublicacaoDTO;
import corallus.artConnect.artConnect.service.PublicacaoService;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

	@Autowired
    private PublicacaoService publicacaoService;

    @PostMapping("/criar-publicacao")
    public PublicacaoDTO criarPublicacao(
    		@RequestPart(value = "legenda", required = false) String legenda,
    		@RequestPart(value = "file", required = false) MultipartFile image,
            @RequestParam Long autorId
    ) {
        return publicacaoService.criarPublicacao(legenda, image, autorId);
    }

    @GetMapping("/todas")
    public List<PublicacaoDTO> listarPublicacoes() {
        return publicacaoService.listarPublicacoes();
    }
}

