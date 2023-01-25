package es.eoi.controller;

import es.eoi.model.Alquiler;
import es.eoi.repository.AlquilerRepository;
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
@RequestMapping("/api/alquiler")
public class APIAlquilerController {

    @Autowired
    AlquilerRepository alquilerRepository;
    @GetMapping("/listaalquileres")
    public ResponseEntity<List<Alquiler>> getAllAlquileres(){
        try {
            List<Alquiler> alquileres = new ArrayList<>();
            alquilerRepository.findAll().forEach(alquileres::add);
            if (alquileres.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(alquileres, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
