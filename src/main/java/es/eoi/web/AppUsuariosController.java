package es.eoi.web;

import es.eoi.model.Usuario;
import es.eoi.service.RoleService;
import es.eoi.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppUsuariosController extends AbstractController<Usuario> {

    private final UsuarioService usuarioService;

    private final RoleService roleService;

    public AppUsuariosController(UsuarioService usuarioService, RoleService roleService) {
        this.usuarioService = usuarioService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String vistaHome(){
        return "index";
    }
}

