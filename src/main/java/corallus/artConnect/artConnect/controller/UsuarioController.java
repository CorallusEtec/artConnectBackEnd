package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.queryFilter.UsuarioFindAllQF;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import corallus.artConnect.artConnect.dto.response.usuario.UsuarioResponse;
import corallus.artConnect.artConnect.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario Controller", description = "Ações reacionada a todos os tipos de usuário do sistema.")
@SecurityRequirement(name = SecurityConfig.SECURITY)
public class UsuarioController {

    private final UsuarioService usuarioService;

    // INJEÇÃO DE DEPENCÊNCIA
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Lista todos os usuários cadastrados no sistema, com sistema de paginação
     * e filtros de busca.
     *
     * @param queryFilter Configuração de filtros de busca.
     * @param pageable Configurações de paginação.
     * @return Lista paginada dos usuários cadastrados no sistema.
     */
    @GetMapping("/findAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
    })
    public ResponseEntity<Page<UsuarioResponse>> findAll(
            @ParameterObject  UsuarioFindAllQF queryFilter,
            @ParameterObject @PageableDefault(sort = "id")
            Pageable pageable
    ) {
        var lista = usuarioService.findAll(queryFilter, pageable);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    /**
     * Busca um usuário pelo Id.
     *
     * @param id Id do usuário buscado.
     * @return Objeto do usuário correnspondente ao Id.
     */
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        UsuarioResponse usuario = this.usuarioService.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
