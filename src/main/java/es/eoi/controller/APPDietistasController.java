package es.eoi.controller;


import es.eoi.model.Dietistas;
import es.eoi.model.Usuario;
import es.eoi.repository.DietistasRepository;
import es.eoi.services.DietistasSrvc;
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
public class APPDietistasController {
    @Autowired
    private DietistasSrvc dietistasSrvc;

    @GetMapping(value="/listadietistas")
    public String listaDietistas(Model mod) {
        List<Dietistas> listaDietistas = dietistasSrvc.getDietistas();
        mod.addAttribute("titulo", "Lista de dietistas");
        mod.addAttribute("dietistas", listaDietistas);
        return "listadietistas";
    }
}
