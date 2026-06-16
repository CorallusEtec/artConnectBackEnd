package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.dto.request.usuario.UserLoginRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UserRegisterRequest;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioLoginResponse;
import corallus.artConnect.artConnect.service.AuthService;
import jakarta.validation.Valid;


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
            UserLoginRequest loginRequest
    ) {
        var response = this.authService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Cadastra um novo usuario no sistema.
     *
     * @param registerRequest Request com os dados de cadastro de um usuário.
     * @return Mensagem caso o cadastro tenha sido efetuado.
     */
    @PostMapping("/register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado | Email já cadastrado.")
    })
	public ResponseEntity<MessageApiResponse> registrar(
        @RequestBody @Valid UserRegisterRequest registerRequest
    ) {
        MessageApiResponse msg = this.authService.register(registerRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}
