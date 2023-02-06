package es.eoi.controller;


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
public class APPDatosBiometricosController extends AbstractController<DatosBiometricos>{
    @Autowired
    // private DatosBiometricos datosBiometricos;
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
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<DatosBiometricos> datosBio = this.datosBiometricosSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (datosBio.isPresent()){
            //Obtenemos el listado de empleados
            List<UsuarioDto> listaUsuarios = this.usuarioSrvc.buscarTodos();
            //Como encontré datos, obtengo el objerto de tipo "GaleriaDto"
            //addAttribute y thymeleaf no  entienden Optional
            DatosBiometricos attr = datosBio.get();
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datos",attr);
            interfazConPantalla.addAttribute("listaUsuarios", listaUsuarios);
            return "datosbio/edit";
        } else{
            //Mostrar página usuario no existe
            return "datosbio/detallesnoencontrado";
        }
    }
    //Me falta un postmaping para guardar
    @PostMapping("/datosbio/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<DatosBiometricos> datosBio = this.datosBiometricosSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
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
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<DatosBiometricos> datosBiometricos = this.datosBiometricosSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (datosBiometricos.isPresent()){
            this.datosBiometricosSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/datosbio";
        } else{
            //Mostrar página usuario no existe
            return "datosbio/detallesnoencontrado";
        }
    }

    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos informacion a la pantalla hay que usar ModelMap
    @GetMapping("/datosbio/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        //Instancia en memoria del dto a informar en la pantalla
        final DatosBiometricos datosBio = new DatosBiometricos();
        //Obtenemos el listado de empleados
        List<DatosBiometricos> datosBioList = this.datosBiometricosSrvc.buscarTodos();
        //obtengo la lista de etiquetas
        List<UsuarioDto> usuariosList = this.usuarioSrvc.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datos", datosBio);
        interfazConPantalla.addAttribute("listadatos", datosBioList);
        interfazConPantalla.addAttribute("listausuarios", usuariosList);
        return "datosbio/registro";
    }

    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/datosbio/registro")
    public String guardarDatos(DatosBiometricos datosBiometricos) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        DatosBiometricos datosBioGuardados =  this.datosBiometricosSrvc.guardar(datosBiometricos);
        Long id = datosBioGuardados.getIdDatosBio();
        return String.format("redirect:/datosbio/%s", id);
    }
}
