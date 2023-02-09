package es.eoi.web.controller;

import es.eoi.dto.RoleDto;
import es.eoi.dto.UsuarioDto;
import es.eoi.dto.UsuarioDtoPsw;
import es.eoi.model.Usuario;
import es.eoi.service.IUsuarioServicio;
import es.eoi.service.RoleService;
import es.eoi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static es.eoi.util.ValidarFormatoPassword.ValidarFormato;

@Controller
public class AppUsuariosSeguridadController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IUsuarioServicio userService;

    private final UsuarioService service;

    private final RoleService roleService;

    public AppUsuariosSeguridadController(UsuarioService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }

    @GetMapping("/usuarios/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        final UsuarioDtoPsw usuarioDtoPsw = new UsuarioDtoPsw();
        final List<RoleDto> roleDTOList = roleService.buscarTodos();
        interfazConPantalla.addAttribute("datosUsuario", usuarioDtoPsw);
        interfazConPantalla.addAttribute("listaRoles", roleDTOList);
        System.out.println("Preparando pantalla de registro");
        return "usuarios/registro";
    }

    @PostMapping("/usuarios/registro")
    public String guardarUsuario(@ModelAttribute(name = "datosUsuario") UsuarioDtoPsw usuarioDtoPsw) throws Exception {
        //Comprobamos el patron
        System.out.println("Guardando usuario antes ");
        System.out.println("Usuario: " + usuarioDtoPsw.getUsername() + " Password: " + usuarioDtoPsw.getPassword());
        if (ValidarFormato(usuarioDtoPsw.getPassword())) {
            Usuario usuario = service.getMapper().toEntityPsw(usuarioDtoPsw);
            System.out.println("Guardando usuario");
            System.out.println("Usuario: " + usuario.getUsername() + " Password: " + usuario.getPassword());
            // Codifico la password
            String encodedPassword = userService.getEncodedPassword(usuario);
            usuarioDtoPsw.setPassword(encodedPassword);
            // Guardo la password
            UsuarioDto usuario1 = this.service.guardar(usuarioDtoPsw);
            return String.format("redirect:/usuarios/%s", usuario1.getId());
        } else {
            return "usuarios/registro";
        }
    }

}
