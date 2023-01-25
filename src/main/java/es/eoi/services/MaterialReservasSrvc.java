package es.eoi.services;

import es.eoi.model.MaterialReservas;
import es.eoi.model.Usuario;
import es.eoi.repository.MaterialReservasRepository;
import es.eoi.repository.UsuarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialReservasSrvc extends AbstractBusinessServiceE<MaterialReservas, Integer, MaterialReservasRepository>{
    private final MaterialReservasRepository materialReservasRepository;

    public MaterialReservasSrvc(MaterialReservasRepository materialReservasRepository) {
        super(materialReservasRepository);
        this.materialReservasRepository = materialReservasRepository;
    }

    public List<MaterialReservas> getMaterialReservas() {
        return materialReservasRepository.findAll();
    }
}
