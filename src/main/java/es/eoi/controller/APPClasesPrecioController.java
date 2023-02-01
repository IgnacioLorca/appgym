package es.eoi.controller;

import es.eoi.model.ClasesPrecio;
import es.eoi.model.Usuario;
import es.eoi.repository.ClasesPrecioRepository;
import es.eoi.services.ClasesPrecioSrvc;
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
public class APPClasesPrecioController {

    @Autowired
    private ClasesPrecioSrvc clasesPrecioSrvc;

    public APPClasesPrecioController(ClasesPrecioSrvc clasesPrecioSrvc) {
        this.clasesPrecioSrvc = clasesPrecioSrvc;
    }

    @GetMapping(value="/listaclasesprecio")
    public String listaClasesPre(Model mod) {
        List<ClasesPrecio> listaClasesPre = clasesPrecioSrvc.getClasesPrecio();
        mod.addAttribute("titulo", "Lista de precios");
        mod.addAttribute("precios", listaClasesPre);
        return "listaclasesprecio";
    }
}
