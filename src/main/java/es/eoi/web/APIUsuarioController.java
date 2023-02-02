package es.eoi.web;


import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/api/usuario")
public class APIUsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        try {
            List<Usuario> usuarios = new ArrayList<>();
            usuarioRepository.findAll().forEach(usuarios::add);
            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
