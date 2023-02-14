package es.eoi.service;



import es.eoi.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface IUsuarioServicio {
    public String getEncodedPassword(Usuario usuario);

    String getEncodedPassword(String password);

    UserDetails loadUserByUsernameError(String email) throws UsernameNotFoundException;
}
