package corallus.artConnect.artConnect.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import corallus.artConnect.artConnect.dto.request.reacao.ReacaoRequest;
import corallus.artConnect.artConnect.dto.response.reacao.ReacaoResponse;
import corallus.artConnect.artConnect.service.ReacaoService;

@RestController
@RequestMapping("/reacoes")
public class ReacaoController {

    private final ReacaoService reacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoController(ReacaoService reacaoService) {
        this.reacaoService = reacaoService;
    }

    @PostMapping("/post/{postId}/reagir")
    public ResponseEntity<ReacaoResponse> reagirPublicacao(@PathVariable Long postId, @RequestBody @Valid ReacaoRequest reacaoPostDTO) {
        ReacaoResponse reacao = this.reacaoService.reagirPublicacao(postId, reacaoPostDTO);
        return new ResponseEntity<>(reacao, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ReacaoResponse> getReacaoPublicacao(@PathVariable Long postId, @RequestParam Long usuarioId) {
        ReacaoResponse reacaoDTO = this.reacaoService.getReacaoPublicacao(postId, usuarioId);
        return new ResponseEntity<>(reacaoDTO, HttpStatus.OK);
    }

    @PostMapping("/comment/{commentId}/reagir")
    public ResponseEntity<ReacaoResponse> reagirComentario(@PathVariable Long commentId, @RequestBody @Valid ReacaoRequest reacaoPostDTO) {
        ReacaoResponse reacao = this.reacaoService.reagirComentario(commentId, reacaoPostDTO);
        return new ResponseEntity<>(reacao, HttpStatus.OK);
    }
    
}
