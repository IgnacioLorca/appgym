package es.eoi.controller;

import es.eoi.model.PerfilUsuario;
import es.eoi.repository.PerfilUsuarioRepository;
import es.eoi.services.PerfilUsuarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class APPPerfilUsuarioController extends AbstractController<PerfilUsuario>{
    @Autowired
    private PerfilUsuarioSrvc perfilUsuarioSrvc;

    @GetMapping(value="/listaperfilusuario")
    public String listaPerfilUs(Model mod) {
        List<PerfilUsuario> listaPerfilUs = perfilUsuarioSrvc.getPerfilUsuario();
        mod.addAttribute("titulo", "Lista de perfiles");
        mod.addAttribute("perfilusuarios", listaPerfilUs);
        return "listaperfilusuarios";
    }
}
