package corallus.artConnect.artConnect.controller;

import corallus.artConnect.artConnect.dto.request.denuncia.DenunciaPatchStatusRequest;
import corallus.artConnect.artConnect.dto.request.denuncia.DenunciaSaveRequest;
import corallus.artConnect.artConnect.dto.response.denuncia.DenunciaResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.service.DenunciaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/denuncia")
@Tag(name = "Denúncia Controller", description = "Gerencia as ações relacionadas às denuncias do sistema.")
@RestController
public final class DenunciaController {

    private final DenunciaService denunciaService;

    // INJEÇÃO DE DEPENDÊNCIA
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    /** Envia uma denúncia ao administrador do sistema.
     *
     * @param request Objeto de requisição da denúncia.
     * @param usuario Referência do usuario autenticado.
     * @return Mensagem caso a mensagem tenha sido enviada com sucesso.
     */
    @PostMapping("/save")
    public ResponseEntity<MessageApiResponse> save(
            @RequestBody @Valid DenunciaSaveRequest request,
            @AuthenticationPrincipal Usuario usuario
    ) {
        var msg = this.denunciaService.save(usuario, request);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    /** Busca todas as denúncias enviadas por usuarios com paginação.
     *
     * @param pageable Configurações de paginação
     * @return Lista paginada de denúncias.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<DenunciaResponse>> findAll (@PageableDefault Pageable pageable) {
        Page<DenunciaResponse> denuncias = this.denunciaService.findAll(pageable);
        return ResponseEntity.ok(denuncias);
    }


    /** Enpoint usado quando o adminstrador arquiva ou resolve uma denúncia.
     *
     * @param id Id da denuncia.
     * @param request Objeto da requisição;
     * @return Mensagem caso o status da denúncia tenha sido alterado com sucesso.
     */
    @PatchMapping("/status/{id}")
    public ResponseEntity<MessageApiResponse> changeStatus(
            @PathVariable Long id,
            @RequestBody @Valid DenunciaPatchStatusRequest request
    ) {
        var msg = this.denunciaService.changeStatus(id, request);
        return ResponseEntity.ok(msg);
    }
}
