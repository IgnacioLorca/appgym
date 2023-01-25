package es.eoi.controller;

import es.eoi.model.Reservas;
import es.eoi.repository.ReservasRepository;
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
@RequestMapping("/api/reservas")
public class APIReservasController {

    @Autowired
    ReservasRepository reservasRepository;
    @GetMapping("/listareservas")
    public ResponseEntity<List<Reservas>> getAllReservas() {
        try {
            List<Reservas> reservas = new ArrayList<>();
            reservasRepository.findAll().forEach(reservas::add);
            if (reservas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reservas, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
