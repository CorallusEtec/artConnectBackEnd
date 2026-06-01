package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
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
public class AuthController {

    private final AuthService authService;

    // INJEÇÃO DE DEPENDÊNCIA
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioLoginResponse> login(@RequestBody @Valid UserLoginRequest loginRequest) {
        var response = this.authService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/register")
	public ResponseEntity<MessageResponse> registrar(
        @RequestBody @Valid UserRegisterRequest registerRequest
    ) {
        MessageResponse msg = this.authService.register(registerRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}
