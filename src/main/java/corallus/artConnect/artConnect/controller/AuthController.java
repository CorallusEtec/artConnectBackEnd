package corallus.artConnect.artConnect.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import corallus.artConnect.artConnect.dto.request.usuario.UsuarioLoginRequest;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioLoginResponse;
import corallus.artConnect.artConnect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/auth")

@Tag(name = "Auth Controller", description = "Controller responsável pelo cadastro e autenticação dos usuários.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class AuthController {

    private final AuthService authService;

    // INJEÇÃO DE DEPENDÊNCIA
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Realiza a autenticação (login) de um usuário no sistema.
     *
     * @param loginRequest Objeto com as credenciais para login.
     * @return Objeto com o 'token' de autenticação e o Id do usuário.
     */
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Credenciais inválidas.")
    })
    public ResponseEntity<UsuarioLoginResponse> login(
            @RequestBody @Valid
            UsuarioLoginRequest loginRequest
    ) {
        var response = this.authService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /** Cadastra um novo usuario no sistema (NOTA: O campo principal deve ser um objeto
     *  de cadastro do tipo UsuarioRegisterRequest)
     *
     * @param principal Objeto que deve ser mandado como Texto com os dados de cadastro e adicionais
     * @return Mensagem caso o cadastro tenha sido efetuado.
     */
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado | Email já cadastrado.")
    })

	public ResponseEntity<MessageApiResponse> registrar(

            @RequestParam(required = false) MultipartFile fotoPerfil,
            @RequestParam String principal
    ) throws JsonProcessingException {
        MessageApiResponse msg = this.authService.register(fotoPerfil, principal);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}
