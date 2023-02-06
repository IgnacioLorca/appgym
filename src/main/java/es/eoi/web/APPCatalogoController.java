package es.eoi.web;

import es.eoi.model.Catalogo;
import es.eoi.service.CatalogoSrvc;
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
public class APPCatalogoController extends AbstractController<Catalogo> {

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
        Optional<Catalogo> catalogo = this.catalogoSrvc.encuentraPorId(id);
        if (catalogo.isPresent()) {
            List<Catalogo> catalogoList = this.catalogoSrvc.buscarTodos();
            Catalogo attr = catalogo.get();
            interfazConPantalla.addAttribute("catalogo", attr);
            interfazConPantalla.addAttribute("listaCatalogo", catalogoList);
            return "catalogo/edit";
        } else {
            return "catalogo/detallesnoencontrado";
        }
    }

    @PostMapping("/catalogo/{id}")
    public String guardarEdicionDatos(@PathVariable("id") Integer id) throws Exception {
        Optional<Catalogo> catalogo = this.catalogoSrvc.encuentraPorId(id);
        if (catalogo.isPresent()) {
            this.catalogoSrvc.guardar(catalogo.get());
            return String.format("redirect:/catalogo/%s", id);
        } else {
            return "catalogo/detallesnoencontrado";
        }
    }

    @PostMapping("/catalogo/{id}/delete")
    public String eliminarDatos(@PathVariable("id") Integer id) {
        Optional<Catalogo> dto = this.catalogoSrvc.encuentraPorId(id);
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
        List<Catalogo> catalogoList = this.catalogoSrvc.buscarTodos();
        interfazConPantalla.addAttribute("datos", catalogo);
        interfazConPantalla.addAttribute("listacatalogo", catalogoList);
        return "catalogo/registro";
    }

    //El que con los datos de la pantalla guarda la informacion de tipo PostMapping
    @PostMapping("/galeria/registro")
    public String guardarEtiqueta(Catalogo catalogo) throws Exception {
        Catalogo catalogoGuardado = this.catalogoSrvc.guardar(catalogo);
        Long id = catalogoGuardado.getIdCatalogo();
        return String.format("redirect:/catalogo/%s", id);
    }

}
