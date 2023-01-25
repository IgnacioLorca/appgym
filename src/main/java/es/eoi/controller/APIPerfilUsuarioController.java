package es.eoi.controller;

import es.eoi.model.PerfilUsuario;
import es.eoi.repository.PerfilUsuarioRepository;
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
@RequestMapping("/api/perfilusuario")
public class APIPerfilUsuarioController {
    @Autowired
    PerfilUsuarioRepository perfilUsuarioRepository;
    @GetMapping("/listaperfilusuario")
    public ResponseEntity<List<PerfilUsuario>> getAllPerfilUsuario(){
        try {
            List<PerfilUsuario> perfilUsuarios = new ArrayList<>();
            perfilUsuarioRepository.findAll().forEach(perfilUsuarios::add);
            if (perfilUsuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(perfilUsuarios, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
