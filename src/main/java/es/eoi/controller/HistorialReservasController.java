package es.eoi.controller;

import es.eoi.model.HistorialReservas;
import es.eoi.repository.HistorialReservasRepository;
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
@RequestMapping("/api/historialReservas")
public class HistorialReservasController {

    @Autowired
    HistorialReservasRepository historialReservasRepository;
    @GetMapping("/listaHistoriaReservas")
    public ResponseEntity<List<HistorialReservas>> getAllHistorial(){
        try {
            List<HistorialReservas> historial = new ArrayList<>();
            historialReservasRepository.findAll().forEach(historial::add);
            if (historial.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(historial, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
