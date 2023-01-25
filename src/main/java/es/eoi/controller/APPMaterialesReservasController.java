package es.eoi.controller;

import es.eoi.model.MaterialReservas;
import es.eoi.model.Usuario;
import es.eoi.repository.MaterialReservasRepository;
import es.eoi.services.MaterialReservasSrvc;
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
public class APPMaterialesReservasController {

    @Autowired
    private MaterialReservasSrvc materialReservasSrvc;

    @GetMapping(value="/listamaterialesreservados")
    public String listaMaterial(Model mod) {
        List<MaterialReservas> listaMaterial = materialReservasSrvc.getMaterialReservas();
        mod.addAttribute("titulo", "Lista de materiales");
        mod.addAttribute("materiales", listaMaterial);
        return "listamaterialesreservados";
    }

}