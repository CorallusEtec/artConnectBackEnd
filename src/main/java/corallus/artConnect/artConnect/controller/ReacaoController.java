package corallus.artConnect.artConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ReacaoResponse> reagir(@RequestBody @Valid ReacaoRequest reacaoRequest) {
        ReacaoResponse reacao = this.reacaoService.reagir(reacaoRequest);
        return new ResponseEntity<>(reacao, HttpStatus.OK);
    }
}
