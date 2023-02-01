package es.eoi.services;

import es.eoi.model.Reservas;
import es.eoi.model.Usuario;
import es.eoi.repository.ReservasRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservasSrvc extends AbstractBusinessServiceE<Reservas, Integer, ReservasRepository>{
    private final ReservasRepository reservasRepository;

    public ReservasSrvc(ReservasRepository reservasRepository) {
        super(reservasRepository);
        this.reservasRepository = reservasRepository;
    }

    public List<Reservas> getReservas() {
        return reservasRepository.findAll();
    }
}
