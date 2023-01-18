package es.eoi.controller;


import es.eoi.model.Usuario;
import es.eoi.services.UsuarioSrvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Arrays;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioSrvc usuariosrvc;

    @GetMapping(value="/listausuarios")
    public String listaUsus(Model mod) {
        List<Usuario> listusus = usuariosrvc.getUsuarios();
        mod.addAttribute("titulo", "Lista de usuarios");
        mod.addAttribute("usuarios", listusus);
        return "listausuarios";
    }

    /*
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        try {
            List<Usuario> usuarios = usuariosrvc.getUsuarios(); //.findAll();

            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
}
