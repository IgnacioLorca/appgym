package es.eoi.controller;

import es.eoi.model.*;
import es.eoi.repository.CalendarioRepository;
import es.eoi.services.CalendarioSrvc;
import es.eoi.services.EntrenamientosSrvc;
import es.eoi.services.ReservasSrvc;
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
public class APPCalendarioController extends AbstractController<Calendario> {

    @Autowired
    private CalendarioSrvc calendarioSrvc;
    private EntrenamientosSrvc entrenamientosSrvc;
    private UsuarioSrvc usuarioSrvc;
    private ReservasSrvc reservasSrvc;
    private final CalendarioRepository calendarioRepository;

    public APPCalendarioController(CalendarioSrvc calendarioSrvc, EntrenamientosSrvc entrenamientosSrvc, UsuarioSrvc usuarioSrvc,
                                   CalendarioRepository calendarioRepository, ReservasSrvc reservasSrvc) {
        this.calendarioSrvc = calendarioSrvc;
        this.entrenamientosSrvc = entrenamientosSrvc;
        this.usuarioSrvc = usuarioSrvc;
        this.calendarioRepository = calendarioRepository;
        this.reservasSrvc = reservasSrvc;
    }

    @GetMapping("/listacalendario")
    public String vista( Model interfazConPantalla){
        Set<Calendario> calendario = this.calendarioSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listacalendario", calendario);
        return "listacalendario";
    }

    @GetMapping("/calendario/{id}")
    public String vistaDatos(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Calendario> calendario = this.calendarioSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (calendario.isPresent()){
            //Obtenemos el listado de empleados
            List<Usuario> listaUsuarios = this.usuarioSrvc.buscarTodos();
            List<Entrenamientos> listaEntrenamientos = this.entrenamientosSrvc.buscarTodos();
            List<Reservas> listaReservas = this.reservasSrvc.buscarTodos();
            //Como encontré datos, obtengo el objerto de tipo "GaleriaDto"
            //addAttribute y thymeleaf no  entienden Optional
            Calendario attr = calendario.get();
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datos", attr);
            interfazConPantalla.addAttribute("listaUsuarios", listaUsuarios);
            interfazConPantalla.addAttribute("listaEntrenamientos", listaEntrenamientos);
            interfazConPantalla.addAttribute("listaReservas", listaReservas);
            return "calendario/edit";
        } else{
            //Mostrar página usuario no existe
            return "calendario/detallesnoencontrado";
        }
    }
    //Me falta un postmaping para guardar
    @PostMapping("/calendario/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Calendario> calendario = this.calendarioSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (calendario.isPresent()){
            this.calendarioSrvc.guardar(calendario.get());
            return String.format("redirect:/calendario/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "calendario/detallesnoencontrado";
        }
    }

    @PostMapping("/calendario/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Calendario> calendario = this.calendarioSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (calendario.isPresent()){
            this.calendarioSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/calendario";
        } else{
            //Mostrar página usuario no existe
            return "calendario/detallesnoencontrado";
        }
    }

    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos informacion a la pantalla hay que usar ModelMap
    @GetMapping("/calendario/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        //Instancia en memoria del dto a informar en la pantalla
        final Calendario calendario = new Calendario();
        //Obtenemos el listado de empleados
        List<Calendario> calendarioList = this.calendarioSrvc.buscarTodos();
        //obtengo la lista de etiquetas
        List<Usuario> usuariosList = this.usuarioSrvc.buscarTodos();
        List<Entrenamientos> entrenamientosList = this.entrenamientosSrvc.buscarTodos();
        List<Reservas> reservasList = this.reservasSrvc.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datos", calendario);
        interfazConPantalla.addAttribute("listacalendario", calendarioList);
        interfazConPantalla.addAttribute("listausuarios", usuariosList);
        interfazConPantalla.addAttribute("listaentrenamientos", entrenamientosList);
        interfazConPantalla.addAttribute("listareservas", reservasList);
        return "calendario/registro";
    }


    @PostMapping("/calendario/registro")
    public String guardarDatos(Calendario calendario) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        Calendario calendarioGuardado = this.calendarioSrvc.guardar(calendario);
        Long id = calendarioGuardado.getIdCalendario();
        return String.format("redirect:/calendario/%s", id);
    }
}
