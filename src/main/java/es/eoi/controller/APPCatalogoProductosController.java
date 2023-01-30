package es.eoi.controller;

import es.eoi.model.CatalogoProductos;
import es.eoi.model.Usuario;
import es.eoi.repository.CatalogoProductosRepository;
import es.eoi.services.CatalogoProductosSrvc;
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
import java.util.Set;


@Controller
public class APPCatalogoProductosController extends AbstractController<CatalogoProductos>{

    @Autowired
    private CatalogoProductosSrvc catalogoProductosSrvc;

    public APPCatalogoProductosController(CatalogoProductosSrvc catalogoProductosSrvc) {
        this.catalogoProductosSrvc = catalogoProductosSrvc;
    }

    @GetMapping("/listacatalogo")
    public String vista(Model interfazConPantalla) {
        Set<CatalogoProductos> catalogo = catalogoProductosSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listacatalogo", catalogo);
        return "/listacatalogo";
    }

    @GetMapping("/catalogo/{id}")
    public String vistaDatosCatalogo(@PathVariable("id") Integer id, ModelMap interfazConPantalla) {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<CatalogoProductos> catalogoProductos = this.catalogoProductosSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (catalogoProductos.isPresent()) {
            //Obtenemos el listado de empleados
            List<CatalogoProductos> catalogoList = this.catalogoProductosSrvc.buscarTodos();
            CatalogoProductos attr = catalogoProductos.get();
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
        Optional<CatalogoProductos> catalogoProductos = this.catalogoProductosSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (catalogoProductos.isPresent()) {
            this.catalogoProductosSrvc.guardar(catalogoProductos.get());
            return String.format("redirect:/catalogo/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "catalogo/detallesnoencontrado";
        }
    }

    @PostMapping("/catalogo/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id) {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<CatalogoProductos> dto = this.catalogoProductosSrvc.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (dto.isPresent()) {
            this.catalogoProductosSrvc.eliminarPorId(id);
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
        final CatalogoProductos catalogoProductos = new CatalogoProductos();
        //Obtenemos el listado de empleados
        List<CatalogoProductos> catalogoProductosList = this.catalogoProductosSrvc.buscarTodos();
        //obtengo la lista de etiquetas
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datos", catalogoProductos);
        interfazConPantalla.addAttribute("listacatalogo", catalogoProductosList);
        return "catalogo/registro";
    }

    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/galeria/registro")
    public String guardarEtiqueta(CatalogoProductos catalogoProductos) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        CatalogoProductos catalogoProductosGuarado = this.catalogoProductosSrvc.guardar(catalogoProductos);
        Long id = catalogoProductosGuarado.getIdCatalogo();
        return String.format("redirect:/catalogo/%s", id);
    }
}