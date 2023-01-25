package es.eoi.controller;

import es.eoi.model.Reservas;
import es.eoi.model.Usuario;
import es.eoi.repository.ReservasRepository;
import es.eoi.services.ReservasSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Controller
public class APPReservasController extends AbstractController<Reservas>{

    @Autowired
    private ReservasSrvc reservasSrvc;

    @GetMapping(value="/listareservas")
    public String listaReservas(Model mod) {
        List<Reservas> listaReservas = reservasSrvc.getReservas();
        mod.addAttribute("titulo", "Lista de reservas");
        mod.addAttribute("reservas", listaReservas);
        return "listareservas";
    }
}
