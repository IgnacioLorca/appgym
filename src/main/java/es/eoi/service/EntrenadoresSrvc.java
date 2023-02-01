package es.eoi.service;

import es.eoi.model.Entrenadores;
import es.eoi.repository.EntrenadoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadoresSrvc extends AbstractBusinessServiceE<Entrenadores, Integer, EntrenadoresRepository>{
    private final EntrenadoresRepository entrenadoresRepository;

    public EntrenadoresSrvc(EntrenadoresRepository entrenadoresRepository) {
        super(entrenadoresRepository);
        this.entrenadoresRepository = entrenadoresRepository;
    }

    public List<Entrenadores> getEntrenadores() {
        return entrenadoresRepository.findAll();
    }
}
