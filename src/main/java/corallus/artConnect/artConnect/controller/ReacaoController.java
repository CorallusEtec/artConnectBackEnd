package corallus.artConnect.artConnect.controller;

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
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
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
    public ResponseEntity<ReacaoResponse> reagir(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid ReacaoRequest reacaoRequest) {
        ReacaoResponse reacao = this.reacaoService.reagir(usuario.getId(), reacaoRequest);
        return new ResponseEntity<>(reacao, HttpStatus.OK);
    }
}
