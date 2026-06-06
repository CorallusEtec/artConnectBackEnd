package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.service.ReacaoService;

@RestController
@RequestMapping("/reacao")
public class ReacaoController {
    private final ReacaoService reacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoController(ReacaoService reacaoService) {
        this.reacaoService = reacaoService;
    }

    @PostMapping("/reagir")
    public ResponseEntity<MessageResponse> reagir(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid ReacaoRequest reacaoRequest) {
        MessageResponse response = this.reacaoService.reagir(usuario.getId(), reacaoRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
