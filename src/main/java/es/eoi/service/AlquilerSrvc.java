package es.eoi.service;

import es.eoi.model.Alquiler;
import es.eoi.repository.AlquilerRepository;
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
