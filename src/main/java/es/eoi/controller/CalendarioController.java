package es.eoi.controller;

import es.eoi.model.Calendario;
import es.eoi.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3306")
@RestController
@RequestMapping("/api/calendario")
public class CalendarioController {

    @Autowired
    CalendarioRepository calendarioRepository;
    @GetMapping("/listacalendario")
    public ResponseEntity<List<Calendario>> getAllCalendario() {
        try {
            List<Calendario> calendarios = new ArrayList<>();
            calendarioRepository.findAll().forEach(calendarios::add);
            if (calendarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(calendarios, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
