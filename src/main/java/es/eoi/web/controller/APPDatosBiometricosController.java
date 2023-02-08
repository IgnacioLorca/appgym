package es.eoi.web.controller;


import es.eoi.dto.UsuarioDto;
import es.eoi.model.DatosBiometricos;
import es.eoi.service.DatosBiometricosSrvc;
import es.eoi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPDatosBiometricosController extends AbstractController<DatosBiometricos> {
    @Autowired
    private DatosBiometricosSrvc datosBiometricosSrvc;

    private UsuarioService usuarioSrvc;

    public APPDatosBiometricosController(DatosBiometricosSrvc datosBiometricosSrvc, UsuarioService usuarioSrvc) {
        this.datosBiometricosSrvc = datosBiometricosSrvc;
        this.usuarioSrvc = usuarioSrvc;
    }


    @GetMapping("/listadatosbio")
    public String vista( Model interfazConPantalla){
        Set<DatosBiometricos> galeriaDatos = this.datosBiometricosSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listadatosbiometricos", galeriaDatos);
        return "listadatosbio";
    }

    @GetMapping("/datosbio/{id}")
    public String vistaDatosGaleria(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        Optional<DatosBiometricos> datosBio = this.datosBiometricosSrvc.encuentraPorId(id);
        if (datosBio.isPresent()){
            List<UsuarioDto> listaUsuarios = this.usuarioSrvc.buscarTodos();
            DatosBiometricos attr = datosBio.get();
            interfazConPantalla.addAttribute("datos", attr);
            interfazConPantalla.addAttribute("listaUsuarios", listaUsuarios);
            return "datosbio/edit";
        } else{
            //Mostrar página usuario no existe
            return "datosbio/detallesnoencontrado";
        }
    }


    @PostMapping("/datosbio/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<DatosBiometricos> datosBio = this.datosBiometricosSrvc.encuentraPorId(id);
        if (datosBio.isPresent()){
            this.datosBiometricosSrvc.guardar(datosBio.get());
            return String.format("redirect:/datosbio/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "galeria/detallesnoencontrado";
        }
    }

    @PostMapping("/datosbio/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        Optional<DatosBiometricos> datosBiometricos = this.datosBiometricosSrvc.encuentraPorId(id);
        if (datosBiometricos.isPresent()){
            this.datosBiometricosSrvc.eliminarPorId(id);
            return "redirect:/datosbio";
        } else{
            return "datosbio/detallesnoencontrado";
        }
    }

    @GetMapping("/datosbio/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        final DatosBiometricos datosBio = new DatosBiometricos();
        List<DatosBiometricos> datosBioList = this.datosBiometricosSrvc.buscarTodos();
        List<UsuarioDto> usuariosList = this.usuarioSrvc.buscarTodos();
        interfazConPantalla.addAttribute("datos", datosBio);
        interfazConPantalla.addAttribute("listadatos", datosBioList);
        interfazConPantalla.addAttribute("listausuarios", usuariosList);
        return "datosbio/registro";
    }

    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/datosbio/registro")
    public String guardarDatos(DatosBiometricos datosBiometricos) throws Exception {
        DatosBiometricos datosBioGuardados =  this.datosBiometricosSrvc.guardar(datosBiometricos);
        Long id = datosBioGuardados.getIdDatosBio();
        return String.format("redirect:/datosbio/%s", id);
    }
}
