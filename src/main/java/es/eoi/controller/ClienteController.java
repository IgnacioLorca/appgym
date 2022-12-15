package es.eoi.controller;

import es.eoi.model.Cliente;
import es.eoi.repository.ClienteRepository;
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
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;
    @GetMapping("/listaclientes")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        try {
            //generamos el contenedor
            List<Cliente> clientes = new ArrayList<>();
            //Leemos dentro del array el contenido del modelo virtual desde el repositorio
            clienteRepository.findAll().forEach(clientes::add);
            //el que quiere puede codificar un bucle con iteradores sobre  el resultado de oficinaRepository.findAll()

            //gestionar las respuestas
            if (clientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            //hay registros
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
