package corallus.artConnect.artConnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import corallus.artConnect.artConnect.entity.Usuario;
import corallus.artConnect.artConnect.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/logar")
    public ResponseEntity<Usuario> login(@RequestParam String email, @RequestParam String senha) {
        try {
            Usuario usuario = loginService.login(email, senha);
            return new ResponseEntity<>(usuario, HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
