package es.eoi.controller;

import es.eoi.model.ClasesReservas;
import es.eoi.service.ClasesReservasSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class APPClasesReservasController {

    @Autowired
    private ClasesReservasSrvc clasesReservasSrvc;

    public APPClasesReservasController(ClasesReservasSrvc clasesReservasSrvc) {
        this.clasesReservasSrvc = clasesReservasSrvc;
    }

    @GetMapping(value="/listaclasesreservadas")
    public String listaClasesRes(Model mod) {
        List<ClasesReservas> listaClasesRes = clasesReservasSrvc.getClasesReservas();
        mod.addAttribute("titulo", "Lista de clases");
        mod.addAttribute("clases", listaClasesRes);
        return "listaclasesreservadas";
    }
}
