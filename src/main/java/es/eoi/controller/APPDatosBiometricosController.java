package es.eoi.controller;


import es.eoi.model.DatosBiometricos;
import es.eoi.services.DatosBiometricosSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class APPDatosBiometricosController {
    @Autowired
    // private DatosBiometricos datosBiometricos;
    private DatosBiometricosSrvc datosBiometricosSrvc;

    @GetMapping(value="/listadatosbio")
    public String listaDatosBio(Model mod) {
        List<DatosBiometricos> listadatosbio = datosBiometricosSrvc.getDatos();
        mod.addAttribute("titulo", "Lista de datos");
        mod.addAttribute("datosbio", listadatosbio);
        return "listadatosbio";
    }
}
