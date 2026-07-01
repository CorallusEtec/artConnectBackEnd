package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.request.denuncia.DenunciaPatchStatusRequest;
import corallus.artConnect.artConnect.dto.request.publicacao.PublicacaoAdminPatchStatusRequest;
import corallus.artConnect.artConnect.dto.request.usuario.UsuarioAdminEditRequest;
import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.service.AdminService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @apiNote As ações do admin estão passando por manutenção
 * @author SamuMeneDev
 */
@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = SecurityConfig.SECURITY)
@Tag(name = "Admin Controller", description = "Gera relatórios e dá acesso a ações de gerência do administrador.")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    /** Endpoint que traz dados para o dashboard do ADM
     *
     * @return Response com dados dá dashboard
     */
    @GetMapping("/relatorio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403", description = "Não Autorizado (apenas admin)")
    })
    public ResponseEntity<RelatorioResponse> getRelatorio() {
        var relatorio = this.adminService.getRelatorio();
        return  new ResponseEntity<>(relatorio, HttpStatus.OK);
    }

    /** Endpoint onde o administrador consegue alterar dados do usuário
     *
     * @param editRequest Objeto da requisição
     * @param id Id do usuário
     * @return Mensagem caso a edição tenha sido feita com suceso
     */
    @PutMapping("/usuario/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "400", description = "Erro na requisição"),
            @ApiResponse(responseCode = "403", description = "Não Autorizado (apenas admin)")
    })
    public ResponseEntity<MessageApiResponse> editUsuario(@RequestBody UsuarioAdminEditRequest editRequest,  @PathVariable Long id) {
        var msg = this.adminService.editUsuario(id, editRequest);
        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }


    /** Endpoint para o administrador remover a publicações do sistema
     *
     * @param id Id da publicação.
     * @param request Objeto de requisição com o status que será alterado
     * @return Mensagem caso a publicação tenha sido excluída.
     */
    @PatchMapping("/publicacao/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403", description = "Não Autorizado (apenas admin)"),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    public ResponseEntity<MessageApiResponse> alterStatusPublicacao(
            @PathVariable Long id,
            @RequestBody @Valid PublicacaoAdminPatchStatusRequest request
    ) {
        var msg = this.adminService.alterStatusPublicacaoById(id, request);
        return ResponseEntity.ok(msg);
    }

    @PatchMapping("/denuncia/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", useReturnTypeSchema = true),
            @ApiResponse(responseCode = "403", description = "Não Autorizado (apenas admin)"),
            @ApiResponse(responseCode = "404", description = "Denuncia não encontrada")
    })
    public ResponseEntity<MessageApiResponse> alterStatusDenunciaById(
            @PathVariable Long id,
            @RequestBody @Valid DenunciaPatchStatusRequest request
    ) {
        var msg = this.adminService.alterStatusDenunciaById(id, request);
        return  ResponseEntity.ok(msg);
    }
}
