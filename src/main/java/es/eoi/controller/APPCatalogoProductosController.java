package es.eoi.controller;

import es.eoi.model.CatalogoProductos;
import es.eoi.model.Usuario;
import es.eoi.repository.CatalogoProductosRepository;
import es.eoi.services.CatalogoProductosSrvc;
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
public class APPCatalogoProductosController {

    @Autowired
    private CatalogoProductosSrvc catalogoProductosSrvc;

    @GetMapping(value="/listacatalogo")
    public String listaCatalogo(Model mod) {
        List<CatalogoProductos> listaCatalogo = catalogoProductosSrvc.getCatalogoProductos();
        mod.addAttribute("titulo", "Lista de productos");
        mod.addAttribute("catalogo", listaCatalogo);
        return "listacatalogo";
    }
}
