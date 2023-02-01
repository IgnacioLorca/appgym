package es.eoi.controller;

import es.eoi.model.MaterialReservas;
import es.eoi.service.MaterialReservasSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class APPMaterialesReservasController {

    @Autowired
    private MaterialReservasSrvc materialReservasSrvc;

    public APPMaterialesReservasController(MaterialReservasSrvc materialReservasSrvc) {
        this.materialReservasSrvc = materialReservasSrvc;
    }

    @GetMapping(value="/listamaterialesreservados")
    public String listaMaterial(Model mod) {
        List<MaterialReservas> listaMaterial = materialReservasSrvc.getMaterialReservas();
        mod.addAttribute("titulo", "Lista de materiales");
        mod.addAttribute("materiales", listaMaterial);
        return "listamaterialesreservados";
    }

}