package es.eoi.controller;

import es.eoi.model.MaterialReservas;
import es.eoi.repository.MaterialReservasRepository;
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
@RequestMapping("/api/reservasmaterial")
public class APIMaterialesReservasController {

    @Autowired
    MaterialReservasRepository materialReservasRepository;
    @GetMapping("/listamaterialesreservados")
    public ResponseEntity<List<MaterialReservas>> getAllMatRes(){
        try {
            List<MaterialReservas> materialesReservas = new ArrayList<>();
            materialReservasRepository.findAll().forEach(materialesReservas::add);
            if (materialesReservas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(materialesReservas, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
