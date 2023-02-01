package es.eoi.controller;

import es.eoi.model.Catalogo;
import es.eoi.repository.CatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/catalogo")
public class APICatalogoController {

    @Autowired
    CatalogoRepository catalogoRepository;
    @GetMapping("/listacatalogo")
    public ResponseEntity<List<Catalogo>> getAllCatalogo(){
        try {
            List<Catalogo> catalogo = new ArrayList<>();
            catalogoRepository.findAll().forEach(catalogo::add);
            if (catalogo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(catalogo, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
