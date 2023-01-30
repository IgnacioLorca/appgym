package es.eoi.controller;

import es.eoi.model.TipoProducto;
import es.eoi.model.Usuario;
import es.eoi.repository.TipoProductoRepository;
import es.eoi.services.TipoProductoSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class APPTipoProductoController extends AbstractController<TipoProducto>{

    @Autowired
    private TipoProductoSrvc tipoProductoSrvc;

    public APPTipoProductoController(TipoProductoSrvc tipoProductoSrvc) {
        this.tipoProductoSrvc = tipoProductoSrvc;
    }

    @GetMapping(value="/listaproductos")
    public String listaTipoProducto(Model mod) {
        List<TipoProducto> listTipoProd = tipoProductoSrvc.getTipoProducto();
        mod.addAttribute("titulo", "Lista de productos");
        mod.addAttribute("productos", listTipoProd);
        return "listaproductos";
    }
}
