package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Reação Controller", description = "Controla as ações de reação de recursos (Publicações, comentários etc) no sistema.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class ReacaoController {
    private final ReacaoService reacaoService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ReacaoController(ReacaoService reacaoService) {
        this.reacaoService = reacaoService;
    }

    /**
     * Adiciona uma reação numa publicação ou um comentário.
     *
     * @param usuario Referência do usuário autênticado, que fará a reação.
     * @param reacaoRequest Request com os dados para realizar a reação.
     * @return Mensagem caso a reação tenha sito feita.
     *
     * @apiNote O conteúdo da mensagem poderá ser "Reagido" ou "Desreagido"
     * de acordo com a ação do usuário.
     */
    @PostMapping("/reagir")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = " Reagido | Desreagido"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Não autenticado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrada.")
    })
    public ResponseEntity<MessageApiResponse> reagir(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid ReacaoRequest reacaoRequest) {
        MessageApiResponse response = this.reacaoService.reagir(usuario.getId(), reacaoRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
