package es.eoi.service;

import es.eoi.dto.ClienteDto;
import es.eoi.model.Cliente;

import es.eoi.repository.ClienteRepository;
import es.eoi.service.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteSrvc extends AbstractBusinessService<Cliente, Integer, ClienteDto, ClienteRepository, ClienteMapper>{

    public ClienteSrvc(ClienteRepository repo, ClienteMapper serviceMapper, ClienteRepository clienteRepository) {
        super(repo, serviceMapper);
    }
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getCliente() {
        return clienteRepository.findAll();
    }
}

