package es.eoi.controller;

import es.eoi.model.Recetas;
import es.eoi.repository.RecetasRespository;
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
@RequestMapping("/api/recetas")
public class APIRecetasController {
    @Autowired
    RecetasRespository recetasRespository;
    @GetMapping("/listarecetas")
    public ResponseEntity<List<Recetas>> getAllRecetas(){
        try{
            List<Recetas> recetas = new ArrayList<>();
            recetasRespository.findAll().forEach(recetas::add);
            if(recetas.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recetas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
