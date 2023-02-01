package es.eoi.controller;

import es.eoi.model.Alquiler;
import es.eoi.service.AlquilerSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class APPAlquilerController {

    @Autowired
    private AlquilerSrvc alquilerSrvc;

    public APPAlquilerController(AlquilerSrvc alquilerSrvc) {
        this.alquilerSrvc = alquilerSrvc;
    }

    @GetMapping(value="/listaalquileres")
    public String listaAlquiler(Model mod) {
        List<Alquiler> listaAlquiler = alquilerSrvc.getAlquiler();
        mod.addAttribute("titulo", "Lista de alquileres");
        mod.addAttribute("alquileres", listaAlquiler);
        return "listalquileres";
    }
}
