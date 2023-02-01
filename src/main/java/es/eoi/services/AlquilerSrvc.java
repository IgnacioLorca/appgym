package es.eoi.services;

import es.eoi.model.Alquiler;
import es.eoi.model.Usuario;
import es.eoi.repository.AlquilerRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerSrvc extends AbstractBusinessServiceE<Alquiler, Integer, AlquilerRepository>{
    private final AlquilerRepository alquilerRepository;

    public AlquilerSrvc(AlquilerRepository alquilerRepository) {
        super(alquilerRepository);
        this.alquilerRepository = alquilerRepository;
    }

    public List<Alquiler> getAlquiler() {
        return alquilerRepository.findAll();
    }
}
