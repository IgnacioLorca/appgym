package es.eoi.web.controller;


import es.eoi.dto.*;
import es.eoi.model.Usuario;
import es.eoi.service.RoleService;
import es.eoi.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {

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
                this.usuarioService.buscarTodos(PageRequest.of(pagina,maxelementos));
        interfazConPantalla.addAttribute(pageNumbersAttributeKey,dameNumPaginas(usuarioDtoPage));
        interfazConPantalla.addAttribute("listausuarios", usuarioDtoPage);
        return "usuarios/listausuariospagina";
    }

    @GetMapping("/usuarios/{idusr}")
    public String vistaDatosUsuario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(id);
        if (usuarioDto.isPresent()){
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);
            return "usuarios/edit";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }

    @GetMapping("/usuarios/registro")
    public String vistaRegistro(Model interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final UsuarioDtoPsw usuarioDtoPsw = new UsuarioDtoPsw();
        //Obtengo la lista de roles
        final List<RoleDto> roleDtoList = roleService.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosUsuario",usuarioDtoPsw);
        interfazConPantalla.addAttribute("listaRoles",roleDtoList);
        return "usuarios/registro";
    }

    @PostMapping("/usuarios/registro")
    public String guardarUsuario( UsuarioDtoPsw usuarioDtoPsw) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        UsuarioDto usuarioGuardado =  this.usuarioService.guardar(usuarioDtoPsw);
        Long id = usuarioGuardado.getId();
        //return "usuarios/detallesusuario";
        return String.format("redirect:/usuarios/%s", id);
    }

    @PostMapping("/usuarios/{idusr}/delete")
    public String eliminarDatosUsuario(@PathVariable("idusr") Integer id){
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(id);
        if (usuarioDto.isPresent()){
            this.usuarioService.eliminarPorId(id);
            return "redirect:/usuarios";
        } else{
            return "usuarios/detallesusuarionoencontrado";
        }
    }

    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada) throws Exception {
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(id);
        if (usuarioDtoControl.isPresent()){
            UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
            usuarioDtoGuardar.setId(id);
            usuarioDtoGuardar.setEmail(usuarioDtoEntrada.getEmail());
            Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity((int) usuarioDtoGuardar.getId());
            if(usuario.isPresent()){
                this.usuarioService.guardar(usuarioDtoGuardar,usuario.get().getPassword());
            }
            else {
                this.usuarioService.guardar(usuarioDtoGuardar);
            }
            return String.format("redirect:/usuarios/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }

    @GetMapping("/usuarios/editmultiple")
    public String mostrarEditMultipleForm(Model intefrazConPantalla) {
        UsuariosListaDto usuariosListaDto = new UsuariosListaDto(this.usuarioService.buscarTodos());
        intefrazConPantalla.addAttribute("form", usuariosListaDto);
        return "usuarios/listaeditableusuarios";
    }

    @PostMapping("/usuarios/savemultiple")
    public String saveListaUsuariuos(@ModelAttribute UsuariosListaDto usuariosListaDto) {
        usuarioService.guardar(usuariosListaDto.getUsuarioDtos());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/login")
    public String vistaLogin(){
        return "usuarios/login";
    }

    @PostMapping("/usuarios/login")
    public String validarPasswordPst(@ModelAttribute(name = "loginForm" ) LoginDto loginDto) {
        String usr = loginDto.getUsername();
        System.out.println("usr :" + usr);
        String password = loginDto.getPassword();
        System.out.println("pass :" + password);
        //¿es correcta la password?
        if (usuarioService.getRepo().repValidarPassword(usr, password) > 0)
        {
            return "index";
        }else {
            return "usuarios/login";
        }
    }
}

