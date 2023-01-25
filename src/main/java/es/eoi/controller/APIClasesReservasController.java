package es.eoi.controller;

import es.eoi.model.ClasesReservas;
import es.eoi.repository.ClasesReservasRepository;
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
@RequestMapping("/api/reservasclase")
public class APIClasesReservasController {

    @Autowired
    ClasesReservasRepository clasesReservasRepository;
    @GetMapping("/listaclasesreservadas")
    public ResponseEntity<List<ClasesReservas>> getAllClasesRes(){
        try {
            List<ClasesReservas> clasesReservas = new ArrayList<>();
            clasesReservasRepository.findAll().forEach(clasesReservas::add);
            if (clasesReservas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(clasesReservas, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
