package es.eoi.controller;

import es.eoi.model.ClasesPrecio;
import es.eoi.repository.ClasesPrecioRepository;
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
@RequestMapping("/api/clasesprecio")
public class APIClasesPrecioController {

    @Autowired
    ClasesPrecioRepository clasesPrecioRepository;
    @GetMapping("/listaclases")
    public ResponseEntity<List<ClasesPrecio>> getAllClases(){
        try {
            List<ClasesPrecio> clases = new ArrayList<>();
            clasesPrecioRepository.findAll().forEach(clases::add);
            if (clases.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clases, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
