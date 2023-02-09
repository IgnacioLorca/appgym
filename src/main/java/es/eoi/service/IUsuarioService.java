package es.eoi.service;

import es.eoi.model.Usuario;

public interface IUsuarioService {
    public String getEncodedPassword(Usuario usuario);
}
