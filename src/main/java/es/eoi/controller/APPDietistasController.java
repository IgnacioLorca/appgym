package es.eoi.controller;


import es.eoi.model.Dietistas;
import es.eoi.model.Entrenadores;
import es.eoi.model.Usuario;
import es.eoi.repository.DietistasRepository;
import es.eoi.services.DietistasSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class APPDietistasController extends AbstractController<Dietistas>{
    @Autowired
    private DietistasSrvc dietistasSrvc;

    public APPDietistasController(DietistasSrvc dietistasSrvc) {
        this.dietistasSrvc = dietistasSrvc;
    }

    @GetMapping("/dietistas")
    public String vistaDietistas(@RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    ModelMap interfazConPantalla){

        Integer pagina = 0;
        if (page.isPresent()) {
            pagina = page.get() -1;
        }
        Integer maxelementos = 10;
        if (size.isPresent()) {
            maxelementos = size.get();
        }
        Page<Dietistas> dietistasPage =
                this.dietistasSrvc.buscarTodos(PageRequest.of(pagina,maxelementos));
        interfazConPantalla.addAttribute(pageNumbersAttributeKey,dameNumPaginas(dietistasPage));
        interfazConPantalla.addAttribute("lista", dietistasPage);
        return "dietistas/listadietistas";
    }

    @GetMapping("/dietistas/{id}")
    public String vistaDatos(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Dietistas> dietistas = this.dietistasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (dietistas.isPresent()){
            //Como encontré datos, obtengo el objerto de tipo "UsuarioDto"
            //addAttribute y thymeleaf no  entienden Optional
            Dietistas attr = dietistas.get();
            //Obtenemos el listado de empleados para la edicion
            List<Dietistas> dietistasList = this.dietistasSrvc.buscarTodos();
            //Enviamos la lista a la pantalla
            interfazConPantalla.addAttribute("listadietistas", dietistasList);
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datos", attr);
            return "dietistas/edit";
        } else{
            //Mostrar página usuario no existe
            return "dietistas/detallesnoencontrado";
        }
    }


    @PostMapping("/dietistas/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<Dietistas> dietistas = this.dietistasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (dietistas.isPresent()){
            Dietistas dietistaGuardar =  new Dietistas();
            dietistaGuardar.setIdDietista(id);
            dietistaGuardar.setNombreDietista(dietistas.get().getNombreDietista());
            this.dietistasSrvc.guardar(dietistaGuardar);
            return String.format("redirect:/dietistas/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "dietistas/detallesnoencontrado";
        }
    }

    @PostMapping("/dietistas/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id) {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Dietistas> dietistas = this.dietistasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (dietistas.isPresent()) {
            this.dietistasSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/dietistas";
        } else {
            //Mostrar página usuario no existe
            return "dietistas/detallesnoencontrado";
        }

    }
}
