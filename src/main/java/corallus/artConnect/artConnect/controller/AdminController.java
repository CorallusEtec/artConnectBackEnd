package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.config.SecurityConfig;
import corallus.artConnect.artConnect.dto.response.admin.RelatorioResponse;
import corallus.artConnect.artConnect.service.AdminService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
