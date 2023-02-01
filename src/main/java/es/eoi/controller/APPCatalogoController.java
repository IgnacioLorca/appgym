package es.eoi.controller;

import es.eoi.model.Catalogo;
import es.eoi.service.CatalogoSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPCatalogoController extends AbstractController<Catalogo>{

    @Autowired
    private CatalogoSrvc catalogoSrvc;

    public APPCatalogoController(CatalogoSrvc catalogoSrvc) {
        this.catalogoSrvc = catalogoSrvc;
    }

    @GetMapping("/listacatalogo")
    public String vista(Model interfazConPantalla) {
        Set<Catalogo> catalogo = catalogoSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listacatalogo", catalogo);
        return "/listacatalogo";
    }

    @GetMapping("/catalogo/{id}")
    public String vistaDatosCatalogo(@PathVariable("id") Integer id, ModelMap interfazConPantalla) {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Catalogo> catalogo = this.catalogoSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (catalogo.isPresent()) {
            //Obtenemos el listado de empleados
            List<Catalogo> catalogoList = this.catalogoSrvc.buscarTodos();
            Catalogo attr = catalogo.get();
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("catalogo", attr);
            interfazConPantalla.addAttribute("listaCatalogo", catalogoList);
            return "catalogo/edit";
        } else {
            //Mostrar página usuario no existe
            return "catalogo/detallesnoencontrado";
        }
    }

    //Me falta un postmaping para guardar
    @PostMapping("/catalogo/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Catalogo> catalogo = this.catalogoSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (catalogo.isPresent()) {
            this.catalogoSrvc.guardar(catalogo.get());
            return String.format("redirect:/catalogo/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "catalogo/detallesnoencontrado";
        }
    }

    @PostMapping("/catalogo/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id) {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<Catalogo> dto = this.catalogoSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (dto.isPresent()) {
            this.catalogoSrvc.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/catalogo";
        } else {
            //Mostrar página usuario no existe
            return "catalogo/detallesnoencontrado";
        }
    }

    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos informacion a la pantalla hay que usar ModelMap
    @GetMapping("/catalogo/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        //Instancia en memoria del dto a informar en la pantalla
        final Catalogo catalogo = new Catalogo();
        //Obtenemos el listado de empleados
        List<Catalogo> catalogoList = this.catalogoSrvc.buscarTodos();
        //obtengo la lista de etiquetas
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datos", catalogo);
        interfazConPantalla.addAttribute("listacatalogo", catalogoList);
        return "catalogo/registro";
    }

    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/galeria/registro")
    public String guardarEtiqueta(Catalogo catalogo) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        Catalogo catalogoGuardado = this.catalogoSrvc.guardar(catalogo);
        Long id = catalogoGuardado.getIdCatalogo();
        return String.format("redirect:/catalogo/%s", id);
    }
}