package es.eoi.controller;


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
        //tenemos que leer la lista de usuarios
        //Que elemento me la ofrece?
        //listaUsrTodos
        //List<UsuarioDto>  lusrdto = this.service.listaUsrTodos();
        //interfazConPantalla.addAttribute("listausuarios", lusrdto);
        //Obetenemos el objeto Page del servicio
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
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            //Como encontré datos, obtengo el objerto de tipo "UsuarioDto"
            //addAttribute y thymeleaf no  entienden Optional
            UsuarioDto attr = usuarioDto.get();
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datosUsuario", attr);
            return "usuarios/edit";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }


    //Para crear un usuario hay dos bloques
    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos informacion a la pantalla hay que usar ModelMap
    @GetMapping("/usuarios/registro")
    public String vistaRegistro(Model interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final UsuarioDtoPsw usuarioDtoPsw = new UsuarioDtoPsw();
        //Obtengo la lista de roles
        final List<RoleDTO> roleDTOList = roleService.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosUsuario",usuarioDtoPsw);
        interfazConPantalla.addAttribute("listaRoles",roleDTOList);
        return "usuarios/registro";
    }
    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/usuarios/registro")
    public String guardarUsuario( UsuarioDtoPsw usuarioDtoPsw) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        UsuarioDto usuarioGuardado =  this.usuarioService.guardar(usuarioDtoPsw);
        Long id = usuarioGuardado.getId();
        //return "usuarios/detallesusuario";
        return String.format("redirect:/usuarios/%s", id);
    }

    //Otro modo de registro con modelandview
    /*
    @GetMapping("/usuarios/registromnv")
    public ModelAndView vistaRegistroMnv(ModelAndView mnv){
        //Instancia en memoria del dto a informar en la pantalla
        final UsuarioDtoPsw usuarioDtoPsw = new UsuarioDtoPsw();
        //Obtengo la lista de roles
        final List<RoleDTO> roleDTOList = roleService.buscarTodos();
        //Objeto para recibir los elementos seleccionados
        List<RoleDTO> checkedRoles = new ArrayList<>();
        //Mediante "addAttribute" comparto con la pantalla
        mnv.addObject("checkedRoles",checkedRoles);
        mnv.addObject("listaRoles",roleDTOList);
        mnv.addObject("datosUsuario",usuarioDtoPsw);
        mnv.setViewName("/usuarios/registro_1");
        return mnv;
    }
    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/usuarios/registromnv")
    public ModelAndView checked(ModelAndView mnv, @ModelAttribute CheckForm form,
                                @ModelAttribute CheckUsuario usuario) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        final  UsuarioDtoPsw usuarioDtoPswg = usuario.getDatosUsuario();
        usuarioDtoPswg.setRoles(form.getCheckedRolesSet());
        UsuarioDto usuarioGuardado =  this.service.guardar(usuarioDtoPswg);
        Long id = usuarioGuardado.getId();
        mnv.setViewName(String.format("/usuarios/%s", id));
        return mnv;
    }

    @Setter
    public static class CheckForm {

        private List<RoleDTO> checkedRoles = new ArrayList<>();

        public List<RoleDTO> getCheckedRoles() {
            return checkedRoles;
        }

        public Set<RoleDTO> getCheckedRolesSet() {
            Set<RoleDTO> roleDTOSet = new HashSet(this.checkedRoles);
            return roleDTOSet;
        }
    }
    @Getter
    @Setter
    public static class CheckUsuario {
        //Instancia en memoria del dto a informar en la pantalla
        private UsuarioDtoPsw datosUsuario = new UsuarioDtoPsw();
    }
    */
    @PostMapping("/usuarios/{idusr}/delete")
    public String eliminarDatosUsuario(@PathVariable("idusr") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            this.usuarioService.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/usuarios";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }
    //Me falta un postmaping para guardar
    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada) throws Exception {
        //Cuidado que la password no viene
        //Necesitamos copiar la información que llega menos la password
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDtoControl.isPresent()){
            //LLamo al método del servicioi para guardar los datos
            UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
            usuarioDtoGuardar.setId(id);
            usuarioDtoGuardar.setEmail(usuarioDtoEntrada.getEmail());
            //Obtenemos la password del sercio
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
    // Lista múltiple de edición
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
    //Controlador de Login
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

