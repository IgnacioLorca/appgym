package es.eoi.controller;

import es.eoi.model.ClasesReservas;
import es.eoi.model.Usuario;
import es.eoi.repository.ClasesReservasRepository;
import es.eoi.services.ClasesReservasSrvc;
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
public class APPClasesReservasController {

    @Autowired
    private ClasesReservasSrvc clasesReservasSrvc;

    @GetMapping(value="/listaclasesreservadas")
    public String listaClasesRes(Model mod) {
        List<ClasesReservas> listaClasesRes = clasesReservasSrvc.getClasesReservas();
        mod.addAttribute("titulo", "Lista de clases");
        mod.addAttribute("clases", listaClasesRes);
        return "listaclasesreservadas";
    }
}
