package es.eoi.controller;

import es.eoi.model.*;
import es.eoi.repository.RecetasRespository;
import es.eoi.services.DietistasSrvc;
import es.eoi.services.EntrenamientosSrvc;
import es.eoi.services.RecetasSrvc;
import es.eoi.services.UsuarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPRecetasController extends AbstractController<Recetas>{
    @Autowired
   private RecetasSrvc recetasSrvc;

    private DietistasSrvc dietistasSrvc;
    private UsuarioSrvc usuarioSrvc;

    public APPRecetasController(RecetasSrvc recetasSrvc, DietistasSrvc dietistasSrvc, UsuarioSrvc usuarioSrvc) {
        this.recetasSrvc = recetasSrvc;
        this.dietistasSrvc = dietistasSrvc;
        this.usuarioSrvc = usuarioSrvc;
    }

    @GetMapping("/listarecetas")
    public String vista( Model interfazConPantalla){
        Set<Recetas> recetas = this.recetasSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listarecetas", recetas);
        return "listarecetas";
    }

    @GetMapping("/reservas/{id}")
    public String vistaDatos(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Recetas> recetas = this.recetasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (recetas.isPresent()){
            //Obtenemos el listado de empleados
            List<Usuario> listaUsuarios = this.usuarioSrvc.buscarTodos();
            List<Recetas> listaRecetas = this.recetasSrvc.buscarTodos();
            List<Dietistas> listaDietistas = this.dietistasSrvc.buscarTodos();
            //Como encontré datos, obtengo el objerto de tipo "GaleriaDto"
            //addAttribute y thymeleaf no  entienden Optional
            Recetas attr = recetas.get();
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datos", attr);
            interfazConPantalla.addAttribute("listaUsuarios", listaUsuarios);
            interfazConPantalla.addAttribute("listaRecetas", listaRecetas);
            interfazConPantalla.addAttribute("listaDietistas", listaDietistas);
            return "recetas/edit";
        } else{
            //Mostrar página usuario no existe
            return "recetas/detallesnoencontrado";
        }
    }
    //Me falta un postmaping para guardar
    @PostMapping("/recetas/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Recetas> recetas = this.recetasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (recetas.isPresent()){
            this.recetasSrvc.guardar(recetas.get());
            return String.format("redirect:/recetas/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "recetas/detallesnoencontrado";
        }
    }

    @PostMapping("/recetas/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Recetas> recetas = this.recetasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (recetas.isPresent()){
            this.recetasSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/recetas";
        } else{
            //Mostrar página usuario no existe
            return "recetas/detallesnoencontrado";
        }
    }

    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos informacion a la pantalla hay que usar ModelMap
    @GetMapping("/recetas/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        //Instancia en memoria del dto a informar en la pantalla
        final Recetas recetas = new Recetas();
        List<Usuario> usuariosList = this.usuarioSrvc.buscarTodos();
        List<Dietistas> dietistasList = this.dietistasSrvc.buscarTodos();
        List<Recetas> recetasList = this.recetasSrvc.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datos", recetas);
        interfazConPantalla.addAttribute("listausuarios", usuariosList);
        interfazConPantalla.addAttribute("listadietistas", dietistasList);
        interfazConPantalla.addAttribute("listarecetas", recetasList);
        return "recetas/registro";
    }


    @PostMapping("/recetas/registro")
    public String guardarDatos(Recetas recetas) throws Exception {
        Recetas recetasGuardadas = this.recetasSrvc.guardar(recetas);
        Long id = recetasGuardadas.getIdReceta();
        return String.format("redirect:/recetas/%s", id);
    }
}
