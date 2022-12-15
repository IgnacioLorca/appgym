package es.eoi.controller;

import es.eoi.model.Oficina;
import es.eoi.repository.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/oficina")
public class OficinaController {

    @Autowired
    OficinaRepository oficinaRepository;
    @GetMapping("/listaoficinas")
    public ResponseEntity<List<Oficina>> getAllOficinas(){
        try {
            //generamos el contenedor
            List<Oficina> oficinas = new ArrayList<>();

            //Leemos dentro del array el contenido del modelo virtual desde el repositorio
            oficinaRepository.findAll().forEach(oficinas::add);
            //el que quiere puede codificar un bucle con iteradores sobre  el resultado de oficinaRepository.findAll()

            //gestionar las respuestas
            if (oficinas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            //hay registros
            return new ResponseEntity<>(oficinas, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
