package es.eoi.web.controller;

import es.eoi.dto.RoleDTO;
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
import java.util.Optional;

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
        final List<RoleDTO> roleDTOList = roleService.buscarTodos();
        interfazConPantalla.addAttribute("datosUsuario", usuarioDtoPsw);
        interfazConPantalla.addAttribute("listaRoles", roleDTOList);
        System.out.println("Preparando pantalla de registro");
        return "usuarios/registro";
    }

    @PostMapping("/usuarios/registro")
    public String guardarUsuario(@ModelAttribute(name = "datosUsuario") UsuarioDtoPsw usuarioDtoPsw) throws Exception {
        // Comprobar que el usuario no está ya dado de alta; es decir que no se ha registrado previamente
        // Hay que verificar que el correo no existe ya en la BBDD
        Optional<Usuario> usu = this.service.getRepo().findUsuarioByUsername(usuarioDtoPsw.getUsername());
        if(usu.isPresent()) {
            // Si el correo SI existe, indica que el usuario ya se dio de alta previamente y le redirigimos al formulario
            // de recordar contraseña.

            return null;
        } else {
            // Si el correo NO existe, consideramos que es un usuario nuevo y se le puede dar de alta y se le redirige a la
            // pantalla de espera ser validado.

            // Codifico la password y la asigno al usuario
            String encodedPassword = userService.getEncodedPassword(usuarioDtoPsw.getPassword());
            usuarioDtoPsw.setPassword(encodedPassword);
            // Las dos anteriores lineas se pueden escribir como
            // usuarioDtoPsw.setPassword(userService.getEncodedPassword(usuarioDtoPsw.getPassword()))

            // El usuario se guardo como no permitido - pendiente de ser validado
            usuarioDtoPsw.setAprobado(false);
            // Guardo la password
            UsuarioDto usuario1 = this.service.guardar(usuarioDtoPsw);
            return "usuarios/pendienteadmision";
        }
    }
}
