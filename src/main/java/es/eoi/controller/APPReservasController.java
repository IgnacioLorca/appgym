package es.eoi.controller;

import es.eoi.model.Calendario;
import es.eoi.model.Entrenamientos;
import es.eoi.model.Reservas;
import es.eoi.model.Usuario;

import es.eoi.service.CalendarioSrvc;
import es.eoi.service.EntrenamientosSrvc;
import es.eoi.service.ReservasSrvc;
import es.eoi.service.UsuarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPReservasController extends AbstractController<Reservas>{

    @Autowired
    private ReservasSrvc reservasSrvc;
    private CalendarioSrvc calendarioSrvc;
    private UsuarioSrvc usuarioSrvc;
    private EntrenamientosSrvc entrenamientosSrvc;

    public APPReservasController(ReservasSrvc reservasSrvc, CalendarioSrvc calendarioSrvc, UsuarioSrvc usuarioSrvc, EntrenamientosSrvc entrenamientosSrvc) {
        this.reservasSrvc = reservasSrvc;
        this.calendarioSrvc = calendarioSrvc;
        this.usuarioSrvc = usuarioSrvc;
        this.entrenamientosSrvc = entrenamientosSrvc;
    }

    @GetMapping("/listareservas")
    public String vista( Model interfazConPantalla){
        Set<Reservas> reservas = this.reservasSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listareservas", reservas);
        return "listareservas";
    }

    @GetMapping("/reservas/{id}")
    public String vistaDatos(@PathVariable("id") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Reservas> reservas = this.reservasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (reservas.isPresent()){
            List<Reservas> listaReservas = this.reservasSrvc.buscarTodos();
            List<Usuario> listaUsuarios = this.usuarioSrvc.buscarTodos();
            List<Calendario> listaCalendario = this.calendarioSrvc.buscarTodos();
            List<Entrenamientos> listaEntrenamientos = this.entrenamientosSrvc.buscarTodos();
            //Como encontré datos, obtengo el objerto de tipo "GaleriaDto"
            //addAttribute y thymeleaf no  entienden Optional
            Reservas attr = reservas.get();
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datos", reservas);
            interfazConPantalla.addAttribute("listaUsuarios", listaUsuarios);
            interfazConPantalla.addAttribute("listaReservas", listaReservas);
            interfazConPantalla.addAttribute("listaEntrenamientos", listaEntrenamientos);
            interfazConPantalla.addAttribute("listacalendario", listaCalendario);
            return "reservas/edit";
        } else{
            //Mostrar página usuario no existe
            return "reservas/detallesnoencontrado";
        }
    }
    //Me falta un postmaping para guardar
    @PostMapping("/reservas/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Reservas> reservas = this.reservasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (reservas.isPresent()){
            this.reservasSrvc.guardar(reservas.get());
            return String.format("redirect:/reservas/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "reservas/detallesnoencontrado";
        }
    }

    @PostMapping("/reservas/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Reservas> reservas = this.reservasSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (reservas.isPresent()){
            this.reservasSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/reservas";
        } else{
            //Mostrar página usuario no existe
            return "reservas/detallesnoencontrado";
        }
    }

    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos informacion a la pantalla hay que usar ModelMap
    @GetMapping("/reservas/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        //Instancia en memoria del dto a informar en la pantalla
        final Reservas reservas = new Reservas();
        //Obtenemos el listado de empleados
        List<Calendario> calendarioList = this.calendarioSrvc.buscarTodos();
        //obtengo la lista de etiquetas
        List<Usuario> usuariosList = this.usuarioSrvc.buscarTodos();
        List<Entrenamientos> entrenamientosList = this.entrenamientosSrvc.buscarTodos();
        List<Reservas> reservasList = this.reservasSrvc.buscarTodos();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datos", reservas);
        interfazConPantalla.addAttribute("listacalendario", calendarioList);
        interfazConPantalla.addAttribute("listausuarios", usuariosList);
        interfazConPantalla.addAttribute("listaentrenamientos", entrenamientosList);
        interfazConPantalla.addAttribute("listareservas", reservasList);
        return "reservas/registro";
    }


    @PostMapping("/reservas/registro")
    public String guardarDatos(Reservas reservas) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        Reservas reservasGuardadas = this.reservasSrvc.guardar(reservas);
        Long id = reservasGuardadas.getIdReservas();
        return String.format("redirect:/reservas/%s", id);
    }
}
