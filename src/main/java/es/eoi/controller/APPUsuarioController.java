package es.eoi.controller;


import es.eoi.model.Dietistas;
import es.eoi.model.Entrenadores;
import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import es.eoi.services.DietistasSrvc;
import es.eoi.services.EntrenadoresSrvc;
import es.eoi.services.UsuarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class APPUsuarioController extends AbstractController<Usuario>{
    @Autowired
    private UsuarioSrvc usuariosrvc;

    private EntrenadoresSrvc entrenadoresSrvc;

    private DietistasSrvc dietistasSrvc;


    public APPUsuarioController(UsuarioSrvc usuariosrvc, EntrenadoresSrvc entrenadoresSrvc, DietistasSrvc dietistasSrvc) {
        this.usuariosrvc = usuariosrvc;
        this.entrenadoresSrvc = entrenadoresSrvc;
        this.dietistasSrvc = dietistasSrvc;
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
        Page<Usuario> usuarioPage =
                this.usuariosrvc.buscarTodos(PageRequest.of(pagina,maxelementos));
        interfazConPantalla.addAttribute(pageNumbersAttributeKey,dameNumPaginas(usuarioPage));
        interfazConPantalla.addAttribute("listausuarios", usuarioPage);
        return "usuarios/listausuariospagina";
    }


    @GetMapping("/usuarios/{idusr}")
    public String vistaDatosUsuario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        Optional<Usuario> usuario = this.usuariosrvc.encuentraPorId(id);

        if (usuario.isPresent()){
            Usuario attr = usuario.get();
            List<Usuario> usuarioList = this.usuariosrvc.buscarTodos();
            List<Entrenadores> entrenadoresList = this.entrenadoresSrvc.buscarTodos();
            List<Dietistas> dietistasList = this.dietistasSrvc.buscarTodos();
            //Enviamos la lista a la pantalla
            interfazConPantalla.addAttribute("listausuarios", usuarioList);
            interfazConPantalla.addAttribute("listadietistas", dietistasList);
            interfazConPantalla.addAttribute("listaempleados", entrenadoresList);
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/edit";
        } else{
            return "usuarios/detallesusuarionoencontrado";
        }
    }


    @GetMapping("/registro")
    public String vistaRegistro(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final Usuario usuarioPsw = new Usuario();
        final List<Usuario> listaUsuario = this.usuariosrvc.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosUsuario", usuarioPsw);
        interfazConPantalla.addAttribute("listaUsuario", listaUsuario);
        return "usuarios/registro";
    }


    @PostMapping("/registro")
    public String guardarUsuario( Usuario usuario) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        Usuario usuarioGuardado =  this.usuariosrvc.guardar(usuario);
        Long id = usuarioGuardado.getIdUsuario();
        //return "usuarios/detallesusuario";
        return String.format("redirect:/usuarios/%s", id);
    }

    @PostMapping("/usuarios/{idusr}/delete")
    public String eliminarDatosUsuario(@PathVariable("idusr") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Usuario> usuario = this.usuariosrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuario.isPresent()){
            this.usuariosrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/usuarios";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }
    //Me falta un postmaping para guardar
    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id) throws Exception {
        //Cuidado que la password no viene
        //Necesitamos copiar la información que llega menos la password
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Usuario> usuario = this.usuariosrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuario.isPresent()){
            //LLamo al método del servicioi para guardar los datos
            Usuario usuarioGuardar =  new Usuario();
            usuarioGuardar.setIdUsuario(id);
            usuarioGuardar.setEmail(usuario.get().getEmail());
            usuarioGuardar.setNombre(usuario.get().getNombre());
            //Obtenemos la password del sercio
            Optional<Usuario> usuarioPsw = this.usuariosrvc.encuentraPorId((int) usuarioGuardar.getIdUsuario());
            if(usuario.isPresent()){
                this.usuariosrvc.guardar(usuarioPsw,usuario.get().getPassword());
            }
            else {
                this.usuariosrvc.guardar(usuarioGuardar);
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
        Usuario usuariosLista = new Usuario();
        intefrazConPantalla.addAttribute("form", usuariosLista);
        return "usuarios/listaeditableusuarios";
    }

    @PostMapping("/usuarios/savemultiple")
    public String saveListaUsuariuos(@ModelAttribute Usuario e) {
        this.usuariosrvc.guardar(usuariosrvc.getUsuarios());
        return "redirect:/usuarios";
    }

    //Controlador de Login
    @GetMapping("/usuarios/login")
    public String vistaLogin(){
        return "usuarios/login";
    }

    @PostMapping("/usuarios/loginEnt")
    public String validarPasswordPst(@ModelAttribute(name = "loginForm" ) Usuario usuario) {
        String usr = usuario.getEmail();
        System.out.println("usuario :" + usr);
        String password = usuario.getPassword();
        System.out.println("password :" + password);
        //¿es correcta la password?
        if (usuariosrvc.getRepo().repValidarPassword(usr, password) > 0) {
            return "index";
        } else {
            return "usuarios/login";
        }
    }
}
