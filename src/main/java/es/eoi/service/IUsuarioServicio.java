package es.eoi.service;



import es.eoi.model.Usuario;


public interface IUsuarioServicio {
    public String getEncodedPassword(Usuario usuario);

    String getEncodedPassword(String password);
}
