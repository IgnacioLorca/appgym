package es.eoi.web;


import es.eoi.model.Contactos;
import es.eoi.service.ContactosSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class APPContactosController {
    @Autowired
    private ContactosSrvc contactosSrvc;

    public APPContactosController(ContactosSrvc contactosSrvc) {
        this.contactosSrvc = contactosSrvc;
    }

    @GetMapping(value="/listacontactos")
    public String listaContactos(Model mod) {
        List<Contactos> listaContactos = contactosSrvc.getListaContactos();
        mod.addAttribute("titulo", "Lista de contactos");
        mod.addAttribute("contactos", listaContactos);
        return "listacontactos";
    }
}
