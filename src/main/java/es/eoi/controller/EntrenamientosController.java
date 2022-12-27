package es.eoi.controller;


import es.eoi.model.Entrenamientos;
import es.eoi.repository.EntrenamientosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3036")
@RestController
@RequestMapping("/api/entrenamientos")
public class EntrenamientosController{
    @Autowired
    EntrenamientosRepository entrenamientosRepository;
    @GetMapping("/listaentrenamientos")
    public ResponseEntity<List<Entrenamientos>> getAllEntrenamientos() {
        try {
            List<Entrenamientos> entrenamientos = new ArrayList<>();
            entrenamientosRepository.findAll().forEach(entrenamientos::add);
            if (entrenamientos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entrenamientos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
