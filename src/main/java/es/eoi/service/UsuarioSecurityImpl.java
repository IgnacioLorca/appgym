package es.eoi.service;

import es.eoi.model.Role;
import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioSecurityImpl implements IUsuarioServicio, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public String getEncodedPassword(Usuario usuario) {
        return null;
    }

    @Override
    public String getEncodedPassword(String clave){
        String encodedPassword = passwordEncoder.encode(clave);
        return encodedPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        System.out.println("loadUserByUsername email: " + email);
        Usuario usuario = usuarioRepository.findUsuarioByEmailAndActiveTrue(email);
        System.out.println("loadUserByUsername usuario: " + usuario.getUsername());

        org.springframework.security.core.userdetails.User springUser=null;

        Set<GrantedAuthority> ga = new HashSet<>();
        for (Role item : usuario.getRoles()){
            ga.add(new SimpleGrantedAuthority(item.getNombreRole()));
        }

        springUser = new org.springframework.security.core.userdetails.User(
                email,
                usuario.getPassword(),
                ga);
        return springUser;
    }

    @Override
    public UserDetails loadUserByUsernameError(String email) throws UsernameNotFoundException {

        System.out.println("loadUserByUsername email : " + email);
        Usuario  usuario= usuarioRepository.findUsuarioByEmailAndActiveTrue(email);
        System.out.println("loadUserByUsername usuario : " + usuario.getUsername());

        org.springframework.security.core.userdetails.User springUser=null;

        Set<GrantedAuthority> ga = new HashSet<>();
        for (Role item : usuario.getRoles()){
            ga.add(new SimpleGrantedAuthority(item.getNombreRole()));
        }
        springUser = new org.springframework.security.core.userdetails.User(
                email,
                usuario.getPassword(),
                ga );
        return springUser;
    }
}
