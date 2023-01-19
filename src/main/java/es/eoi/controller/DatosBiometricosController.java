package es.eoi.controller;

import es.eoi.model.DatosBiometricos;
import es.eoi.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/datosbio")
public class DatosBiometricosController {
    @Autowired
    private DatosBiometricos datosBiometricos;

    @GetMapping(value="/listadatosbio")
    public String listaDatosBio(Model mod) {
        long listadatosbio = datosBiometricos.getIdDatosBio();
        mod.addAttribute("titulo", "Lista de datos");
        mod.addAttribute("datosbio", listadatosbio);
        return "listadatosbio";
    }
}
