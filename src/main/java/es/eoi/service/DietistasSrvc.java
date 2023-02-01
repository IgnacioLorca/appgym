package es.eoi.service;

import es.eoi.model.Dietistas;
import es.eoi.repository.DietistasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietistasSrvc extends AbstractBusinessServiceE<Dietistas, Integer, DietistasRepository>{
    private final DietistasRepository dietistasRepository;

    public DietistasSrvc(DietistasRepository dietistasRepository) {
        super(dietistasRepository);
        this.dietistasRepository = dietistasRepository;
    }

    public List<Dietistas> getDietistas() {
        return dietistasRepository.findAll();
    }
}
