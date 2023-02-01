package es.eoi.controller;

import es.eoi.model.TipoProducto;
import es.eoi.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tiposproducto")
public class APITipoProductoController {

    @Autowired
    TipoProductoRepository tipoProductoRepository;
    @GetMapping("/listaproductos")
    public ResponseEntity<List<TipoProducto>> getAllProductos(){
        try {
            List<TipoProducto> productos = new ArrayList<>();
            tipoProductoRepository.findAll().forEach(productos::add);
            if (productos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(productos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
