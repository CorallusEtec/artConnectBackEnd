package corallus.artConnect.artConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.service.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {

	@Autowired
    private PublicacaoService publicacaoService;

    @PostMapping("/save")
    public ResponseEntity<String> criarPublicacao(
        @RequestPart(value = "legenda", required = false) String legenda,
        @RequestPart(value = "file", required = false) MultipartFile image,
        @RequestParam Long autorId
) {
    String msg = this.publicacaoService.criarPublicacao(legenda, image, autorId);
    return new ResponseEntity<>(msg, HttpStatus.CREATED);
}

    @GetMapping("/findAll")
    public ResponseEntity<List<PublicacaoResponse>> listarPublicacoes(
        @RequestParam(required = false) String nomeArte,
        @RequestParam(required = false) Boolean recentes,
        @RequestParam(required = false) Boolean mostLikeFirst) {
        List<PublicacaoResponse> lista = this.publicacaoService.listarPublicacoes(nomeArte, recentes, mostLikeFirst);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


}

