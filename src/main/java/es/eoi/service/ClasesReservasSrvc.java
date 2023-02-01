package es.eoi.service;

import es.eoi.model.ClasesReservas;
import es.eoi.repository.ClasesReservasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasesReservasSrvc extends AbstractBusinessServiceE<ClasesReservas, Integer, ClasesReservasRepository>{
    private final ClasesReservasRepository clasesReservasRepository;

    public ClasesReservasSrvc(ClasesReservasRepository clasesReservasRepository) {
        super(clasesReservasRepository);
        this.clasesReservasRepository = clasesReservasRepository;
    }

    public List<ClasesReservas> getClasesReservas() {
        return clasesReservasRepository.findAll();
    }
}
