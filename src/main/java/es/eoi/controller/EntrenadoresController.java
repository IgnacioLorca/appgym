package es.eoi.controller;

import es.eoi.model.Entrenadores;
import es.eoi.repository.EntrenadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins="http://localhost:3306")
@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadoresController {
    @Autowired
    EntrenadoresRepository entrenadoresRepository;
    @GetMapping("/listaentrenadores")
    public ResponseEntity<List<Entrenadores>> getAllEntrenadores(){
        try{
            List<Entrenadores> entrenadores = new ArrayList<>();
            entrenadoresRepository.findAll().forEach(entrenadores::add);
            if(entrenadores.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entrenadores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
