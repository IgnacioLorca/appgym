package es.eoi.controller;


import es.eoi.model.ListaContactos;
import es.eoi.repository.ContactosRepository;
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
@RequestMapping("/api/contactos")
public class ContactosController {
    @Autowired
    ContactosRepository contactosRepository;
    @GetMapping("/listacontactos")
    public ResponseEntity<List<ListaContactos>> getAllContactos(){
        try {
            List<ListaContactos> contactos = new ArrayList<>();
            contactosRepository.findAll().forEach(contactos::add);
            if (contactos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contactos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
