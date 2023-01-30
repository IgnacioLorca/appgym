package es.eoi.controller;

import es.eoi.model.Entrenadores;
import es.eoi.model.Usuario;
import es.eoi.repository.EntrenadoresRepository;
import es.eoi.services.EntrenadoresSrvc;
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
public class APPEntrenadoresController extends  AbstractController<Entrenadores>{
    @Autowired
    private EntrenadoresSrvc entrenadoresSrvc;

    public APPEntrenadoresController(EntrenadoresSrvc entrenadoresSrvc) {
        this.entrenadoresSrvc = entrenadoresSrvc;
    }

    @GetMapping("/entrenadores")
    public String vistaEntrenadores(@RequestParam("page") Optional<Integer> page,
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
        Page<Entrenadores> entrenadoresPage =
                this.entrenadoresSrvc.buscarTodos(PageRequest.of(pagina,maxelementos));
        interfazConPantalla.addAttribute(pageNumbersAttributeKey,dameNumPaginas(entrenadoresPage));
        interfazConPantalla.addAttribute("lista", entrenadoresPage);
        return "entrenadores/listaentrenadores";
    }

    @GetMapping("/entrenadores/{id}")
    public String vistaDatos(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Entrenadores> entrenadores = this.entrenadoresSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (entrenadores.isPresent()){
            //Como encontré datos, obtengo el objerto de tipo "UsuarioDto"
            //addAttribute y thymeleaf no  entienden Optional
            Entrenadores attr = entrenadores.get();
            //Obtenemos el listado de empleados para la edicion
            List<Entrenadores> entrenadoresList = this.entrenadoresSrvc.buscarTodos();
            //Enviamos la lista a la pantalla
            interfazConPantalla.addAttribute("listaentrenadores", entrenadoresList);
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datos", attr);
            return "entrenadores/edit";
        } else{
            //Mostrar página usuario no existe
            return "entrenadores/detallesnoencontrado";
        }
    }
    //Me falta un postmaping para guardar

    @PostMapping("/entrenadores/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<Entrenadores> entrenadores = this.entrenadoresSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (entrenadores.isPresent()){
            Entrenadores entrenadorGuardar =  new Entrenadores();
            entrenadorGuardar.setIdEntrenador(id);
            entrenadorGuardar.setNombreEntrenador(entrenadores.get().getNombreEntrenador());
            this.entrenadoresSrvc.guardar(entrenadorGuardar);
            return String.format("redirect:/entrenadores/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "entrenadores/detallesnoencontrado";
        }
    }

    @PostMapping("/entrenadpres/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id) {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Entrenadores> entrenadores = this.entrenadoresSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (entrenadores.isPresent()) {
            this.entrenadoresSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/entrenadores";
        } else {
            //Mostrar página usuario no existe
            return "entrenadores/detallesnoencontrado";
        }

    }
}
