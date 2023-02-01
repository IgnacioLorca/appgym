package es.eoi.service;

import es.eoi.model.Calendario;
import es.eoi.repository.CalendarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioSrvc extends AbstractBusinessServiceE<Calendario, Integer, CalendarioRepository>{
    private final CalendarioRepository calendarioRepository;

    public CalendarioSrvc(CalendarioRepository calendarioRepository) {
        super(calendarioRepository);
        this.calendarioRepository = calendarioRepository;
    }

    public List<Calendario> getCalendario() {
        return calendarioRepository.findAll();
    }
}
