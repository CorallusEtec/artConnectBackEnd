package corallus.artConnect.artConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.publicacao.ReacaoDTO;
import corallus.artConnect.artConnect.dto.publicacao.ReacaoPostDTO;
import corallus.artConnect.artConnect.service.PublicacaoReacaoService;

@RestController
@RequestMapping("/reacoes")
public class PublicacaoReacaoController {
    @Autowired
    private PublicacaoReacaoService publicacaoReacaoService;

    @PostMapping("/{postId}/reagir")
    public ResponseEntity<ReacaoDTO> reagir(@PathVariable Long postId, @RequestBody ReacaoPostDTO reacaoPostDTO) {
        ReacaoDTO reacao = this.publicacaoReacaoService.reagir(postId, reacaoPostDTO);

        return new ResponseEntity<>(reacao, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ReacaoDTO> getReacao(@PathVariable Long postId, @RequestParam Long usuarioId) {
        ReacaoDTO reacaoDTO = this.publicacaoReacaoService.getReacao(postId, usuarioId);

        return new ResponseEntity<>(reacaoDTO, HttpStatus.OK);
    }
}
