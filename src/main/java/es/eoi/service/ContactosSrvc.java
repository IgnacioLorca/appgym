package es.eoi.service;

import es.eoi.model.Contactos;
import es.eoi.repository.ContactosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactosSrvc extends AbstractBusinessServiceE<Contactos, Integer, ContactosRepository>{
    private final ContactosRepository contactosRepository;

    public ContactosSrvc(ContactosRepository contactosRepository) {
        super(contactosRepository);
        this.contactosRepository = contactosRepository;
    }

    public List<Contactos> getListaContactos() {
        return contactosRepository.findAll();
    }
}
