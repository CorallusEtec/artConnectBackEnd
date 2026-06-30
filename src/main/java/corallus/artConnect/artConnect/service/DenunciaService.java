package corallus.artConnect.artConnect.service;

import corallus.artConnect.artConnect.dto.request.denuncia.DenunciaPatchStatusRequest;
import corallus.artConnect.artConnect.dto.request.denuncia.DenunciaSaveRequest;
import corallus.artConnect.artConnect.dto.response.denuncia.DenunciaResponse;
import corallus.artConnect.artConnect.dto.response.util.MessageApiResponse;
import corallus.artConnect.artConnect.entity.Denuncia;
import corallus.artConnect.artConnect.entity.Status;
import corallus.artConnect.artConnect.entity.atores.Usuario;
import corallus.artConnect.artConnect.enumeration.ETipoDenuncia;
import corallus.artConnect.artConnect.enumeration.ETipoStatus;
import corallus.artConnect.artConnect.error.errors.NotAuthorizedException;
import corallus.artConnect.artConnect.error.errors.ResourceNotFoundException;
import corallus.artConnect.artConnect.mapper.denuncia.DenunciaMapper;
import corallus.artConnect.artConnect.repository.DenunciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;
    private final StatusService statusService;
    private final DenunciaMapper denunciaMapper;

    // INJEÇÃO DE DEPÊNDENCIA
    public DenunciaService(DenunciaRepository denunciaRepository, StatusService statusService, DenunciaMapper denunciaMapper) {
        this.denunciaRepository = denunciaRepository;
        this.statusService = statusService;
        this.denunciaMapper = denunciaMapper;
    }

    public MessageApiResponse save(Usuario usuario, DenunciaSaveRequest request) {
        if(Objects.isNull(usuario)) {
            throw new NotAuthorizedException();
        }

        Denuncia denuncia = new Denuncia();

        denuncia.setTipoDenuncia(ETipoDenuncia.valueOf(request.tipoDenuncia()));
        denuncia.setAutor(usuario);
        denuncia.setIdRecurso(request.idRecurso());
        denuncia.setTitulo(request.titulo());
        denuncia.setStatus(this.statusService.generateStatus());


        this.denunciaRepository.save(denuncia);

        return new MessageApiResponse("Denuncia enviada com sucesso");
    }


    public Page<DenunciaResponse> findAll(Pageable pageable) {
        Page<Denuncia> denuncias = this.denunciaRepository.findAll(pageable);
        return denuncias.map(this.denunciaMapper::toDTO);
    }


    public MessageApiResponse changeStatus(Long idDenuncia, DenunciaPatchStatusRequest request) {
        Denuncia denuncia = this.denunciaRepository.findById(idDenuncia)
                .orElseThrow(()->new ResourceNotFoundException("Denuncia não encontrada"));

        Status statusAtual = denuncia.getStatus();
        statusAtual.setTipoStatus(ETipoStatus.valueOf(request.tipoStatus()));

        denuncia.setStatus(this.statusService.modifyStatus(statusAtual.getId(), statusAtual));

        this.denunciaRepository.save(denuncia);

        return new MessageApiResponse("Status da denúncia alterado com sucesso");

    }
}
