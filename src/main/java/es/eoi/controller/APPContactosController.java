package es.eoi.controller;


import es.eoi.dto.UsuarioDto;
import es.eoi.model.Contactos;
import es.eoi.model.DatosBiometricos;
import es.eoi.service.ContactosSrvc;
import es.eoi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPContactosController {
    @Autowired
    private ContactosSrvc contactosSrvc;

    private UsuarioService usuarioSrvc;

    public APPContactosController(ContactosSrvc contactosSrvc, UsuarioService usuarioSrvc) {
        this.contactosSrvc = contactosSrvc;
        this.usuarioSrvc = usuarioSrvc;
    }

    @GetMapping("/listacontactos")
    public String vista( Model interfazConPantalla){
        Set<Contactos> galeriaContactos = this.contactosSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listacontactos", galeriaContactos);
        return "listacontactos";
    }

    @GetMapping("/contactos/{id}")
    public String vistaDatosGaleria(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        Optional<Contactos> contactos = this.contactosSrvc.encuentraPorId(id);
        if (contactos.isPresent()){
            List<UsuarioDto> listaUsuarios = this.usuarioSrvc.buscarTodos();
            Contactos attr = contactos.get();
            interfazConPantalla.addAttribute("datos",attr);
            interfazConPantalla.addAttribute("listaUsuarios", listaUsuarios);
            return "contactos/edit";
        } else{
            return "contactos/detallesnoencontrado";
        }
    }

    @PostMapping("/contactos/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<Contactos> contactos = this.contactosSrvc.encuentraPorId(id);
        if (contactos.isPresent()){
            this.contactosSrvc.guardar(contactos.get());
            return String.format("redirect:/datoscontactos/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "contactos/detallesnoencontrado";
        }
    }

    @PostMapping("/contactos/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        Optional<Contactos> contactos = this.contactosSrvc.encuentraPorId(id);
        if (contactos.isPresent()){
            this.contactosSrvc.eliminarPorId(id);
            return "redirect:/contactos";
        } else{
            //Mostrar página usuario no existe
            return "contactos/detallesnoencontrado";
        }
    }


    @GetMapping("/contactos/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        final Contactos contactos = new Contactos();
        List<Contactos> contactosList = this.contactosSrvc.buscarTodos();
        List<UsuarioDto> usuariosList = this.usuarioSrvc.buscarTodos();
        interfazConPantalla.addAttribute("datos", contactos);
        interfazConPantalla.addAttribute("listacontactos", contactosList);
        interfazConPantalla.addAttribute("listausuarios", usuariosList);
        return "contactos/registro";
    }

    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/contactos/registro")
    public String guardarDatos(Contactos contactos) throws Exception {
        Contactos contactosGuardados =  this.contactosSrvc.guardar(contactos);
        Long id = contactos.getIdRelacion();
        return String.format("redirect:/datosbio/%s", id);
    }
}
