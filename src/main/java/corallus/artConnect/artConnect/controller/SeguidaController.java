package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.error.errors.UserNotFoundException;
import corallus.artConnect.artConnect.repository.SeguidaRepository;
import corallus.artConnect.artConnect.repository.atores.UsuarioRepository;
import corallus.artConnect.artConnect.service.SeguidaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguida")
@Tag(name = "Seguida Controller", description = "Controla as ações de seguir e deixar de seguir usuários.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class SeguidaController {
    private final SeguidaService seguidaService;
    private final SeguidaRepository seguidaRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguidaController(
            SeguidaService seguidaService,
            SeguidaRepository seguidaRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.seguidaService = seguidaService;
        this.seguidaRepository = seguidaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/{seguidoId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Seguindo | Deixou de seguir"),
            @ApiResponse(responseCode = "400", description = "Usuário tentando seguir a si mesmo"),
            @ApiResponse(responseCode = "403", description = "Não autenticado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<MessageApiResponse> seguir(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long seguidoId
    ) {
        MessageApiResponse response = seguidaService.seguir(usuario.getId(), seguidoId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{seguidoId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "true | false"),
            @ApiResponse(responseCode = "403", description = "Não autenticado")
    })
    public ResponseEntity<Boolean> isFollowing(
            @AuthenticationPrincipal Usuario usuario,
            @PathVariable Long seguidoId
    ) {
        Usuario seguido = usuarioRepository.findById(seguidoId)
                .orElseThrow(UserNotFoundException::new);
        boolean result = seguidaRepository.existsBySeguidorAndSeguido(usuario, seguido);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}