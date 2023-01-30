package es.eoi.controller;


import es.eoi.model.Entrenamientos;
import es.eoi.model.Usuario;
import es.eoi.repository.EntrenamientosRepository;
import es.eoi.services.EntrenamientosSrvc;
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
