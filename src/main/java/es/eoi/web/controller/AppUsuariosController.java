package es.eoi.web.controller;


import es.eoi.dto.LoginDto;
import es.eoi.dto.UsuarioDto;
import es.eoi.dto.UsuariosListaDto;
import es.eoi.model.Usuario;
import es.eoi.service.RoleService;
import es.eoi.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {

    private final UsuarioService service;

    private final RoleService roleService;

    public AppUsuariosController(UsuarioService service, RoleService roleService) {
        this.service = service;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String vistaHome(){
        return "index";
    }


    @GetMapping("/usuarios")
    public String vistaUsuarios(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,
                                    ModelMap interfazConPantalla){
        Integer pagina = 1;
        if (page.isPresent()) {
            pagina = page.get() -1;
        }
        Integer maxelementos = 10;
        if (size.isPresent()) {
            maxelementos = size.get();
        }
        Page<UsuarioDto> usuarioDtoPage =
                this.service.buscarTodos(PageRequest.of(pagina,maxelementos));
        interfazConPantalla.addAttribute(pageNumbersAttributeKey,dameNumPaginas(usuarioDtoPage));
        interfazConPantalla.addAttribute("listausuarios", usuarioDtoPage);
        return "usuarios/listausuariospagina";
    }


    @GetMapping("/usuarios/{idusr}")
    public String vistaDatosUsuario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        // Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.service.encuentraPorId(id);
        if (usuarioDto.isPresent()){
            // Como encontré datos, obtengo el objeto de tipo "UsuarioDto"
            UsuarioDto attr = usuarioDto.get();
            // Asigno atributos y muestro
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/edit";
        } else{
            // Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }
    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada) throws Exception {
        // Necesitamos copiar la información que llega menos la password
        // Con el id tengo que buscar la entidad
        Optional<UsuarioDto> usuarioDtoControl = this.service.encuentraPorId(id);
        if (usuarioDtoControl.isPresent()){
            // LLamo al método del servicio para guardar los datos
            UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
            usuarioDtoGuardar.setId(id);
            usuarioDtoGuardar.setEmail(usuarioDtoEntrada.getEmail());
            usuarioDtoGuardar.setUsername(usuarioDtoEntrada.getUsername());
            // Obtenemos la password del servicio
            Optional<Usuario> usuario = service.encuentraPorIdEntity((int) usuarioDtoGuardar.getId());
            if(usuario.isPresent()){
                this.service.guardar(usuarioDtoGuardar,usuario.get().getPassword());
            }
            else {
                this.service.guardar(usuarioDtoGuardar);
            }
            return String.format("redirect:/usuarios/%s", id);
        } else {
            return "usuarios/detallesusuarionoencontrado";
        }
    }
    @PostMapping("/usuarios/{idusr}/delete")
    public String eliminarDatosUsuario(@PathVariable("idusr") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.service.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            this.service.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/usuarios";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }
    @PostMapping("/usuarios/{idusr}/habilitar")
    @PreAuthorize("hasRole('ADMIN')")
    public String habilitarDatosUsuario(@PathVariable("idusr") Integer id){
        // Con el id tengo que buscar el registro a nivel de entidad
        Optional<Usuario> usuario = this.service.encuentraPorIdEntity(id);
        // Compruebo si hay datos
        if (usuario.isPresent()){
            Usuario attr = usuario.get();
            if (attr.isActive()){
                attr.setActive(false);
            }else{
                attr.setActive(true);
            }
            this.service.getRepo().save(attr);
            // Redirijo a la lista de usuarios
            return "redirect:/usuarios";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }


    @GetMapping("/usuarios/editmultiple")
    public String mostrarEditMultipleForm(Model interfazConPantalla) {
        UsuariosListaDto usuariosListaDto = new UsuariosListaDto(this.service.buscarTodos());
        interfazConPantalla.addAttribute("form", usuariosListaDto);
        return "usuarios/listaeditableusuarios";
    }
    @PostMapping("/usuarios/savemultiple")
    public String saveListaUsuarios(@ModelAttribute UsuariosListaDto usuariosListaDto) {
        service.guardar(usuariosListaDto.getUsuarioDtos());
        return "redirect:/usuarios";
    }


    @GetMapping("/usuarios/login")
    public String vistaLogin(Model model){
        model.addAttribute("loginForm",new LoginDto());
        return "usuarios/login";
    }

    @PostMapping("/usuarios/login")
    public String validarPasswordPst(@ModelAttribute(name = "loginForm" ) LoginDto loginDto) {
        String usr = loginDto.getUsername();
        System.out.println("usr :" + usr);
        String password = loginDto.getPassword();
        System.out.println("pass :" + password);
        //¿es correcta la password?
        if (service.getRepo().repValidarPassword(usr, password) > 0)
        {
            return "index";
        }else {
            return "usuarios/login";
        }
    }
}
