package es.eoi.service;

import es.eoi.model.Entrenamientos;
import es.eoi.repository.EntrenamientosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenamientosSrvc extends AbstractBusinessServiceE<Entrenamientos, Integer, EntrenamientosRepository>{
    private final EntrenamientosRepository entrenamientosRepository;

    public EntrenamientosSrvc(EntrenamientosRepository entrenamientosRepository) {
        super(entrenamientosRepository);
        this.entrenamientosRepository = entrenamientosRepository;
    }

    public List<Entrenamientos> getEntrenamientos() {
        return entrenamientosRepository.findAll();
    }
}
