package es.eoi.controller;

import es.eoi.model.PerfilUsuario;
import es.eoi.service.PerfilUsuarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class APPPerfilUsuarioController extends AbstractController<PerfilUsuario>{
    @Autowired
    private PerfilUsuarioSrvc perfilUsuarioSrvc;

    public APPPerfilUsuarioController(PerfilUsuarioSrvc perfilUsuarioSrvc) {
        this.perfilUsuarioSrvc = perfilUsuarioSrvc;
    }

    @GetMapping(value="/listaperfilusuario")
    public String listaPerfilUs(Model mod) {
        List<PerfilUsuario> listaPerfilUs = perfilUsuarioSrvc.getPerfilUsuario();
        mod.addAttribute("titulo", "Lista de perfiles");
        mod.addAttribute("perfilusuarios", listaPerfilUs);
        return "listaperfilusuarios";
    }
}
