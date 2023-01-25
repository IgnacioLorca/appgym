package es.eoi.controller;

import es.eoi.model.Alquiler;
import es.eoi.model.Usuario;
import es.eoi.repository.AlquilerRepository;
import es.eoi.services.AlquilerSrvc;
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
public class APPAlquilerController {

    @Autowired
    private AlquilerSrvc alquilerSrvc;

    @GetMapping(value="/listaalquileres")
    public String listaAlquiler(Model mod) {
        List<Alquiler> listaAlquiler = alquilerSrvc.getAlquiler();
        mod.addAttribute("titulo", "Lista de alquileres");
        mod.addAttribute("alquileres", listaAlquiler);
        return "listalquileres";
    }
}
