package es.eoi.controller;


import es.eoi.model.Entrenamientos;
import es.eoi.service.EntrenamientosSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class APPEntrenamientosController {
    @Autowired
    private EntrenamientosSrvc entrenamientosSrvc;

    public APPEntrenamientosController(EntrenamientosSrvc entrenamientosSrvc) {
        this.entrenamientosSrvc = entrenamientosSrvc;
    }

    @GetMapping(value="/listaentrenamientos")
    public String listaEntrenamientos(Model mod) {
        List<Entrenamientos> listaEntrenamientos = entrenamientosSrvc.getEntrenamientos();
        mod.addAttribute("titulo", "Lista de entrenamientos");
        mod.addAttribute("entrenamientos", listaEntrenamientos);
        return "listaentrenamientos";
    }
}
