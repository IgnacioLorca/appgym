package es.eoi.service;

import es.eoi.model.Usuario;

public interface IUsuarioServicio {

    String getEncodedPassword(String clave);

}
