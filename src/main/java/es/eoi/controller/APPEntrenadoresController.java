package es.eoi.controller;

import es.eoi.model.Entrenadores;
import es.eoi.model.Usuario;
import es.eoi.repository.EntrenadoresRepository;
import es.eoi.services.EntrenadoresSrvc;
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
public class APPEntrenadoresController {
    @Autowired
    private EntrenadoresSrvc entrenadoresSrvc;

    @GetMapping(value="/listaentrenadores")
    public String listaEntrenadores(Model mod) {
        List<Entrenadores> listaEntrenadores = entrenadoresSrvc.getEntrenadores();
        mod.addAttribute("titulo", "Lista de entrenadores");
        mod.addAttribute("entrenadores", listaEntrenadores);
        return "listaentrenadores";
    }

}
