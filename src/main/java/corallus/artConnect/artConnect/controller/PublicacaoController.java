package corallus.artConnect.artConnect.controller;

import java.io.IOException;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.PublicacaoFindAllQF;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import corallus.artConnect.artConnect.dto.response.publicacao.PublicacaoResponse;
import corallus.artConnect.artConnect.service.PublicacaoService;

@RestController
@RequestMapping("/publicacao")
public class PublicacaoController {
    private final PublicacaoService publicacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public PublicacaoController(PublicacaoService publicacaoService) {
        this.publicacaoService = publicacaoService;
    }

    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(
        @RequestPart(value = "legenda", required = false) String legenda,
        @RequestPart(value = "file", required = false) MultipartFile image,
        @RequestPart(value = "tipoMidia", required=false) String tipoMidia,
        @AuthenticationPrincipal Usuario usuario
    ) throws IOException {
        MessageResponse msg = this.publicacaoService.save(legenda, image, tipoMidia, usuario);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<PublicacaoResponse>> findAll(
            PublicacaoFindAllQF find,
            @AuthenticationPrincipal Usuario usuario,
            @PageableDefault(size = 7, sort = "id") Pageable pageable) {
        var lista = this.publicacaoService.findAll(find, usuario, pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}

