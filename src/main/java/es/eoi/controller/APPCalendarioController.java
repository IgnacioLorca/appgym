package es.eoi.controller;

import es.eoi.model.Calendario;
import es.eoi.model.Usuario;
import es.eoi.repository.CalendarioRepository;
import es.eoi.services.CalendarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Controller
public class APPCalendarioController {

    @Autowired
    private CalendarioSrvc calendarioSrvc;

    @GetMapping(value="/listacalendario")
    public String listaCalendario(Model mod) {
        List<Calendario> listaCalendario = calendarioSrvc.getCalendario();
        mod.addAttribute("titulo", "Lista de horarios");
        mod.addAttribute("calendario", listaCalendario);
        return "listacalendario";
    }
}
