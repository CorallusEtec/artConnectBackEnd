package corallus.artConnect.artConnect.controller;

import java.util.List;
import corallus.artConnect.artConnect.dto.response.util.MessageResponse;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.queryFilter.ContratanteFindAllQF;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import corallus.artConnect.artConnect.dto.request.contratante.ContratanteEditRequest;
import corallus.artConnect.artConnect.dto.response.contratante.ContratanteResponse;
import corallus.artConnect.artConnect.service.ContratanteService;

@RequestMapping("/contratante")
@RestController
public class ContratanteController {
    private final ContratanteService contratanteService;

    // INJEÇÃO DE DEPENDÊNCIA
    public ContratanteController(ContratanteService contratanteService) {
        this.contratanteService = contratanteService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ContratanteResponse>> findAll(ContratanteFindAllQF find) {
        List<ContratanteResponse> lista = this.contratanteService.findAll(find);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @PutMapping("/edit")
    public ResponseEntity<MessageResponse> edit(@AuthenticationPrincipal Usuario contratante, @RequestBody @Valid ContratanteEditRequest editRequest) {
        MessageResponse msg = this.contratanteService.edit(contratante.getId(), editRequest);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratanteResponse> find(@PathVariable Long id) {
        ContratanteResponse contratante = this.contratanteService.findById(id);
        return new ResponseEntity<>(contratante, HttpStatus.OK);
    }
}