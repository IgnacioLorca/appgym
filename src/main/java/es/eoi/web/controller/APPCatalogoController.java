package es.eoi.controller;


import es.eoi.dto.CatalogoDto;
import es.eoi.model.Catalogo;
import es.eoi.service.CatalogoSrvc;
import es.eoi.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class APPCatalogoController extends AbstractController<CatalogoDto> {

    @Autowired
    private CatalogoSrvc catalogoSrvc;

    public APPCatalogoController(CatalogoSrvc catalogoSrvc) {
        this.catalogoSrvc = catalogoSrvc;
    }

    @GetMapping("/listacatalogo")
    public String vistaCatalogo(Model interfazConPantalla) {
        Set<CatalogoDto> catalogoDto = catalogoSrvc.buscarTodosSet();
        interfazConPantalla.addAttribute("listacatalogo", catalogoDto);
        return "catalogo/listacatalogo";
    }

    @GetMapping("/catalogo/{id}")
    public String vistaDatosCatalogo(@PathVariable("id") Integer id, ModelMap interfazConPantalla) {
        Optional<CatalogoDto> catalogoDto = this.catalogoSrvc.encuentraPorId(id);
        if (catalogoDto.isPresent()) {
            List<CatalogoDto> catalogoList = this.catalogoSrvc.buscarTodos();
            CatalogoDto attr = catalogoDto.get();
            interfazConPantalla.addAttribute("catalogo", attr);
            interfazConPantalla.addAttribute("listacatalogo", catalogoList);
            return "catalogo/edit";
        } else {
            return "catalogo/detallesnoencontrado";
        }
    }


    @PostMapping("/catalogo/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<CatalogoDto> catalogoDto = this.catalogoSrvc.encuentraPorId(id);
        if (catalogoDto.isPresent()) {
            this.catalogoSrvc.guardar(catalogoDto.get());
            return String.format("redirect:/catalogo/%s", id);
        } else {
            return "catalogo/detallesnoencontrado";
        }
    }

    @PostMapping("/catalogo/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id) {
        Optional<CatalogoDto> dto = this.catalogoSrvc.encuentraPorId(id);
        if (dto.isPresent()) {
            this.catalogoSrvc.eliminarPorId(id);
            return "redirect:/catalogo";
        } else {
            //Mostrar página usuario no existe
            return "catalogo/detallesnoencontrado";
        }
    }


    @GetMapping("/catalogo/registro")
    public String vistaRegistro(Model interfazConPantalla) {
        final Catalogo catalogo = new Catalogo();
        List<CatalogoDto> catalogoList = this.catalogoSrvc.buscarTodos();
        interfazConPantalla.addAttribute("datos", catalogo);
        interfazConPantalla.addAttribute("listacatalogo", catalogoList);
        return "catalogo/registro";
    }



    @PostMapping("/catalogo/registro")
    public String guardarEtiqueta(CatalogoDto catalogoDto) throws Exception {
        CatalogoDto catalogoGuardado = this.catalogoSrvc.guardar(catalogoDto);
        Long id = catalogoGuardado.getIdCatalogo();
        return String.format("redirect:/catalogo/%s", id);
    }
}