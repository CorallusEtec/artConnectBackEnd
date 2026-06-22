package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.request.arte.GeneroArteEditRequest;
import corallus.artConnect.artConnect.dto.request.arte.GeneroArteSaveRequest;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.arte.GeneroArte;
import corallus.artConnect.artConnect.service.GeneroArteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/generoArte")
@RestController
@Tag(name = "Gênero de Arte Controller", description = "Controlla os gêneros de arte existentes no sistema.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class GeneroArteController {

    private final GeneroArteService generoArteService;

    public GeneroArteController(GeneroArteService generoArteService) {
        this.generoArteService = generoArteService;
    }

    /**
     * Retorna a lista de gêneros de artes cadastradas no sistema.
     *
     * @return Lista com os gêneros de artes cadastradas no sistema.
     */
    @GetMapping("/findAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
    })
    public ResponseEntity<List<GeneroArte>> findAll() {
        List<GeneroArte> lista = this.generoArteService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /** Busca lista de generos de arte pelo id da arte.
     *
     * @param id Id da arte.
     * @return Retorna lista de generos relacionados com uma arte.
     */
    @GetMapping("/findByArte/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
    })
    public ResponseEntity<List<GeneroArte>> findByArteId(@PathVariable Long id) {
        var lista = this.generoArteService.findByArteId(id);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /** Busca genero de arte pelo Id.
     *
     * @param id Id do genero de arte.
     * @return Objeto do genero requisitado.
     */
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Gênero não encontrado"),
    })
    public ResponseEntity<GeneroArte> findById(@PathVariable Long id) {
        var genero = this.generoArteService.findById(id);
        return new ResponseEntity<>(genero, HttpStatus.OK);
    }



    /**
     * Cadastra um gênero de arte no sistema.
     *
     * @param saveRequest Request com os dados necessários para cadastro do gênero.
     * @return Mensagem caso o gênero tenha sido salvo com sucesso.
     */
    @PostMapping("/save")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genero de Arte salvo com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "403", description = "Não autenticado")
    })
    public ResponseEntity<MessageApiResponse> save(
            @RequestBody @Valid GeneroArteSaveRequest saveRequest
    ) {
        MessageApiResponse msg = this.generoArteService.save(saveRequest);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /** Exclui um genero de arte pelo Id
     *
     * @param id Id do genero de arte
     * @return Mensagem caso o genero de arte tenha sido deletado com sucesso
     */
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genero de Arte alterada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Não autenticado")
    })
    public ResponseEntity<MessageApiResponse> deleteById(@PathVariable Long id) {
        var msg = this.generoArteService.deleteById(id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genero de Arte alterada com sucesso."),
            @ApiResponse(responseCode = "403", description = "Não autenticado")
    })
    public ResponseEntity<MessageApiResponse> editById(
            @RequestBody GeneroArteEditRequest editRequest,
            @PathVariable Long id) {
        var msg = this.generoArteService.editById(editRequest, id);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}