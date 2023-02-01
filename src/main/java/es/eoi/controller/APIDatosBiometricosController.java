package es.eoi.controller;


import es.eoi.model.DatosBiometricos;
import es.eoi.model.Usuario;
import es.eoi.repository.DatosBiometricosRepostitory;
import es.eoi.services.DatosBiometricosSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/datosbio")
public class APIDatosBiometricosController {
    @Autowired
    // private DatosBiometricos datosBiometricos;
        private DatosBiometricosRepostitory datosBiometricosRepostitory;

    public ResponseEntity<List<DatosBiometricos>> getAllDatosBiometricos(){
        try {
            List<DatosBiometricos> datosBiometricos = new ArrayList<>();
            datosBiometricosRepostitory.findAll().forEach(datosBiometricos::add);
            if (datosBiometricos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(datosBiometricos, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
