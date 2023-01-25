package es.eoi.controller;

import es.eoi.model.Recetas;
import es.eoi.model.Usuario;
import es.eoi.repository.RecetasRespository;
import es.eoi.services.RecetasSrvc;
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
public class APPRecetasController extends AbstractController<Recetas>{
    @Autowired
   private RecetasSrvc recetasSrvc;
    @GetMapping("/listarecetas")
    public String listaRecetas(Model mod) {
        List<Recetas> listaRecetas = recetasSrvc.getRecetas();
        mod.addAttribute("titulo", "Lista de recetas");
        mod.addAttribute("recetas", listaRecetas);
        return "listarecetas";
    }
}
