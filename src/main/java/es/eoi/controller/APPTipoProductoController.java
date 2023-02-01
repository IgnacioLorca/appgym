package es.eoi.controller;

import es.eoi.model.TipoProducto;
import es.eoi.service.TipoProductoSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
