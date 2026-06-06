package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.arte.GeneroArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import corallus.artConnect.artConnect.service.GeneroArteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/generoArte")
@RestController
@Tag(name = "Gênero de Arte Controller", description = "Controlla os gêneros de arte existentes no sistema.")
public class GeneroArteController {

    private final GeneroArteService generoArteService;

    public GeneroArteController(GeneroArteService generoArteService) {
        this.generoArteService = generoArteService;
    }

    /**
     * Retorna a lista de gêneros de artes cadastradas no sistema.
     *
     * @return Lista com os gêneros de arte cadastradas nos sistema.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<GeneroArte>> findAll() {
        List<GeneroArte> lista = this.generoArteService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Cadastra um gênero de arte no sistema.
     *
     * @param saveRequest Request com os dados necessários para cadastro do gênero.
     * @return Mensagem caso o gênero tenha sido salvo com sucesso.
     */
    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(
            @RequestBody @Valid GeneroArteSaveRequest saveRequest
    ) {
        MessageResponse msg = this.generoArteService.save(saveRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }
}