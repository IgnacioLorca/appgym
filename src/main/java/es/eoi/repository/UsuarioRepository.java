package es.eoi.repository;

import es.eoi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
    @Query("Select count(id) from Usuario where email = ?1 and password = ?2")
    Integer repValidarPassword(String email, String password);

    Optional<Usuario> findUsuarioByEmail(String email);
}
